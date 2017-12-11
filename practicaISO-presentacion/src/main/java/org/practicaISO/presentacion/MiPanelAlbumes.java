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
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MiPanelAlbumes extends JPanel {
	private JLabel label;
	private JLabel lblMostrandolbumesQue;
	private JLabel lblNick;
	private JList list;
	private JButton btnReproducirAlbum;
	private JLabel lblMusica;
	private JButton btnParar;
	private JButton btnActualizarLista;

	/**
	 * Create the panel.
	 */
	public MiPanelAlbumes(Cliente client) {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		{
			label = new JLabel("");
			label.setIcon(new ImageIcon(MiPanelAlbumes.class.getResource("/org/practicaISO/presentacion/IconoSpotify.png")));
			label.setBounds(768, 0, 176, 127);
			add(label);
		}
		{
			lblMostrandolbumesQue = new JLabel("Mostrando álbumes que escuchas");
			lblMostrandolbumesQue.setForeground(Color.WHITE);
			lblMostrandolbumesQue.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblMostrandolbumesQue.setBounds(58, 26, 528, 58);
			add(lblMostrandolbumesQue);
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
			actualizarModelo(client);
		}
		
		{
			btnReproducirAlbum = new JButton("Reproducir álbum");
			btnReproducirAlbum.setIcon(new ImageIcon(MiPanelAlbumes.class.getResource("/org/practicaISO/presentacion/IconoPlay.png")));
			btnReproducirAlbum.setEnabled(false);
			btnReproducirAlbum.addActionListener(new BtnReproducirAlbumActionListener());
			btnReproducirAlbum.setBounds(648, 168, 176, 47);
			add(btnReproducirAlbum);
		}
		{
			lblMusica = new JLabel("");
			lblMusica.setForeground(Color.WHITE);
			lblMusica.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblMusica.setBounds(58, 442, 786, 38);
			add(lblMusica);
		}
		{
			btnParar = new JButton("Parar");
			btnParar.setIcon(new ImageIcon(MiPanelAlbumes.class.getResource("/org/practicaISO/presentacion/IconoParar.png")));
			btnParar.addActionListener(new BtnPararActionListener());
			btnParar.setEnabled(false);
			btnParar.setBounds(648, 246, 176, 47);
			add(btnParar);
		}
		{
			btnActualizarLista = new JButton("Actualizar lista");
			btnActualizarLista.addActionListener(new BtnActualizarListaActionListener1());
			btnActualizarLista.setIcon(new ImageIcon(MiPanelAlbumes.class.getResource("/org/practicaISO/presentacion/IconoActualizar.png")));
			btnActualizarLista.setBounds(648, 329, 197, 47);
			add(btnActualizarLista);
		}
		
		

	}
	
	public void actualizarModelo(Cliente client) {
		GestorCliente gc = new GestorCliente();
		GestorCancion gcan= new GestorCancion();
		GestorAlbum galb = new GestorAlbum();
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
					Album alb= galb.obtenerAlbum(canc);
					modelo.addElement(alb.toString());
						
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
			JOptionPane.showMessageDialog(this, "No hay albumes que mostrar");
		}
		list.setModel(modelolimpio);
	}
	
	private class BtnReproducirAlbumActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnReproducirAlbum.setEnabled(false);
			GestorCancion gcan = new GestorCancion();
			String album = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(album, "-");
			Album alb = new Album(Integer.parseInt(token.nextToken()));
			try {
				ArrayList<String> idscanciones= gcan.obteneridsAlbum(alb);
				Cancion canc = new Cancion(Integer.parseInt(idscanciones.get(0)));
				canc=gcan.obtenerCancionId(canc);
				lblMusica.setText("Reproduciendo lista aleatoria: "+canc.getTitulo()+" - "+canc.getAutor()+" del álbum con id "+canc.getAlbum());
				btnParar.setEnabled(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
	private class BtnPararActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnParar.setEnabled(false);
			btnReproducirAlbum.setEnabled(true);
			lblMusica.setText("");
		}
	}
	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnReproducirAlbum.setEnabled(true);
		}
	}
	private class BtnActualizarListaActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Cliente client = new Cliente(lblNick.getText());
			actualizarModelo(client);
			btnReproducirAlbum.setEnabled(false);
			btnParar.setEnabled(false);
		}
	}
}
