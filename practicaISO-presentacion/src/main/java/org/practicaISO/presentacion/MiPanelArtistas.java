package org.practicaISO.presentacion;

import javax.swing.JPanel;

import org.practicaISO.dominio.Album;
import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.GestorAlbum;
import org.practicaISO.persistencia.GestorCancion;
import org.practicaISO.persistencia.GestorCliente;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiPanelArtistas extends JPanel {
	private JLabel label;
	private JLabel lblMostrandoArtistasQue;
	private JLabel lblNick;
	private JList list;
	private JButton btnParar;
	private JButton btnReproducir;
	private JLabel lblReproduciendo;

	/**
	 * Create the panel.
	 */
	public MiPanelArtistas(Cliente client) {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		{
			label = new JLabel("");
			label.setIcon(new ImageIcon(MiPanelArtistas.class.getResource("/org/practicaISO/presentacion/Spotify_icon-icons.com_66783.png")));
			label.setBounds(768, 0, 176, 127);
			add(label);
		}
		{
			lblMostrandoArtistasQue = new JLabel("Mostrando artistas que escuchas");
			lblMostrandoArtistasQue.setForeground(Color.WHITE);
			lblMostrandoArtistasQue.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblMostrandoArtistasQue.setBounds(58, 26, 528, 58);
			add(lblMostrandoArtistasQue);
		}
		{
			lblNick = new JLabel((String) null);
			lblNick.setForeground(Color.WHITE);
			lblNick.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			lblNick.setBounds(58, 89, 242, 38);
			lblNick.setText(client.getNick());
			add(lblNick);
		}
		{
			list = new JList();
			list.addListSelectionListener(new ListListSelectionListener());
			list.setForeground(SystemColor.menu);
			list.setFont(new Font("Tahoma", Font.BOLD, 17));
			list.setBackground(Color.GRAY);
			list.setBounds(58, 168, 559, 233);
			add(list);
		}
		GestorCliente gc = new GestorCliente();
		GestorCancion gcan= new GestorCancion();
		ArrayList<Cancion> ac=null;
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		DefaultListModel<String> modelolimpio = new DefaultListModel<String>();
		try {
			ac=gc.obtenerCanciones(client);		
		} catch (Exception e) {
			System.out.println("Error al obtener canciones del usuario");
		}
		if(ac!=null) {
			for (Cancion canc : ac) {
				try {
					canc=gcan.obtenerCancionId(canc);
					modelo.addElement(canc.getAutor());

				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
			//para no repetir albumes
			for (int ii=0;ii<modelo.size();ii++) {
				for (int jj=0;jj<modelo.size();jj++) {
					if(!modelo.get(ii).equals(modelo.get(jj))) {
						if(!modelolimpio.contains(modelo.get(jj))) {
							modelolimpio.addElement(modelo.get(jj));
						}
					}
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay artistas que mostrar");
		}
		list.setModel(modelolimpio);
		
		
		
		{
			btnParar = new JButton("Parar");
			btnParar.addActionListener(new BtnPararActionListener());
			btnParar.setEnabled(false);
			btnParar.setBounds(668, 237, 176, 58);
			add(btnParar);
		}
		{
			btnReproducir = new JButton("Reproducir album");
			btnReproducir.addActionListener(new BtnReproducirActionListener());
			btnReproducir.setEnabled(false);
			btnReproducir.setBounds(668, 168, 176, 47);
			add(btnReproducir);
		}
		{
			lblReproduciendo = new JLabel("");
			lblReproduciendo.setForeground(Color.WHITE);
			lblReproduciendo.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblReproduciendo.setBounds(58, 434, 842, 38);
			add(lblReproduciendo);
		}

	}
	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnReproducir.setEnabled(true);
		}
	}
	private class BtnPararActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnParar.setEnabled(false);
			btnReproducir.setEnabled(true);
			lblReproduciendo.setText("");
		}
	}
	private class BtnReproducirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnReproducir.setEnabled(false);
			btnParar.setEnabled(true);
			GestorCancion gcan = new GestorCancion();
			String autor = list.getSelectedValue().toString();
			ArrayList<Cancion> canciones = new ArrayList<Cancion>();
			try {
				canciones=gcan.obtenerCancionesAutor(autor);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			lblReproduciendo.setText("Reproduciendo lista aleatoria "+canciones.get(0).getTitulo()+" - "+canciones.get(0).getAutor());
		}
	}
}
