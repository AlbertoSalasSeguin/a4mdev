package org.practicaISO.presentacion;

import javax.swing.JPanel;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.GestorAlbum;
import org.practicaISO.persistencia.GestorCancion;
import org.practicaISO.persistencia.GestorCliente;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class MiPanelComprar extends JPanel {
	private JList list;
	private JButton btnComprarCancin;
	private JLabel lblNick;
	private JLabel lblEsta;
	private JButton btnReproducir;
	private JLabel lblEscuchando;
	private JButton btnParar;
	private JLabel lblicon;

	public MiPanelComprar(Cliente client) {
		setBackground(Color.DARK_GRAY);
		setMinimumSize(new Dimension(104, 20));
		{
			list = new JList();
			list.setFont(new Font("Tahoma", Font.BOLD, 17));
			list.setBounds(58, 153, 568, 334);
			list.setBackground(new Color(105, 105, 105));
			list.addListSelectionListener(new ListListSelectionListener());
			setLayout(null);
			{
				lblNick = new JLabel(client.getNick());
				lblNick.setForeground(Color.WHITE);
				lblNick.setMinimumSize(new Dimension(104, 20));
				lblNick.setPreferredSize(new Dimension(104, 20));
				lblNick.setBounds(58, 89, 242, 38);
				lblNick.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
				add(lblNick);
			}
			list.setForeground(UIManager.getColor("Label.background"));
			add(list);
		}
		{
			GestorCancion gc = new GestorCancion();
			ArrayList<Cancion> ac=null;
			DefaultListModel<String> modelo = new DefaultListModel<String>();
			try {
				ac=gc.leerCanciones();		
			} catch (Exception e) {
				System.out.println("Error ");
			}
			if(ac!=null) {
				for (Cancion canc : ac) {
					modelo.addElement(canc.toStringCompra());
				}
			}else {
				JOptionPane.showMessageDialog(this, "No hay canciones que mostrar");
			}
			list.setModel(modelo);

		}
		{
			btnComprarCancin = new JButton("Comprar canción");
			btnComprarCancin.setBounds(661, 395, 243, 80);
			btnComprarCancin.addActionListener(new BtnComprarCancinActionListener());
			btnComprarCancin.setEnabled(false);
			add(btnComprarCancin);
		}
		{
			lblEsta = new JLabel("Estas son las canciones más escuchadas,");
			lblEsta.setForeground(Color.WHITE);
			lblEsta.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblEsta.setBounds(58, 26, 548, 58);
			add(lblEsta);
		}
		{
			btnParar = new JButton("Parar");
			btnParar.setVisible(false);
			btnParar.addActionListener(new BtnPararActionListener());
			btnParar.setEnabled(false);
			btnParar.setBounds(804, 165, 112, 80);
			add(btnParar);
		}
		{
			btnReproducir = new JButton("Reproducir");
			btnReproducir.setVisible(false);
			btnReproducir.setEnabled(false);
			btnReproducir.addActionListener(new BtnReproducirActionListener());			
			btnReproducir.setBounds(655, 165, 118, 80);
			add(btnReproducir);
		}
		{
			lblEscuchando = new JLabel("");
			lblEscuchando.setForeground(Color.WHITE);
			lblEscuchando.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblEscuchando.setBounds(58, 500, 568, 37);
			add(lblEscuchando);
		}
		{
			lblicon = new JLabel("");
			lblicon.setIcon(new ImageIcon(MiPanelComprar.class.getResource("/org/practicaISO/presentacion/Spotify_icon-icons.com_66783.png")));
			lblicon.setBounds(768, 0, 176, 127);
			add(lblicon);
		}
		
		if(client.getSuscripcion().equals("Premium")) {
			btnReproducir.setVisible(true);
			btnParar.setVisible(true);
			
		}
//944 550
	}
	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnComprarCancin.setEnabled(true);
			btnReproducir.setEnabled(true);
			Cliente client = new Cliente(lblNick.getText());
			GestorCliente gc = new GestorCliente();
			try {
				client=gc.obtenerCliente(client);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if(client.getSuscripcion().equals("Premium")) {
				btnReproducir.setVisible(true);
				btnParar.setVisible(true);
			}
		}
	}

	private class BtnComprarCancinActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String cancion = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(cancion, "-");
			GestorCliente gc = new GestorCliente();
			GestorCancion gcan = new GestorCancion();
			Cancion canc = new Cancion(Integer.parseInt(token.nextToken()));
			Cliente client = new Cliente(lblNick.getText());
			try {
				canc=gcan.obtenerCancion(canc);
			} catch (Exception e2) {
				e2.printStackTrace();
			}	
			ArrayList<String> ids=gcan.obtenerIds(client);
			if (esComprada(ids,canc)) {
				JOptionPane.showMessageDialog(null, "Esta canción ya ha sido adquirida");
			} else {
				int confirmado = JOptionPane.showConfirmDialog(null, "¿Deseas adquirir esta canción?","Ventana de confirmación",JOptionPane.YES_NO_OPTION);
				if (confirmado==JOptionPane.YES_OPTION) {


					try {
						gc.comprarCancion(client, canc);

						JOptionPane.showMessageDialog(null, "Enhorabuena has comprado "+canc.getTitulo()+ " de "+canc.getAutor());
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}
		}

		private boolean esComprada(ArrayList<String> ids, Cancion canc) {
			boolean comprado = false;
			for(int i=0;i<ids.size();i++) {
				if(canc.getIdCancion()==Integer.parseInt(ids.get(i).toString())) {
					comprado=true;
				}
			}
			return comprado;
		}
	}
	private class BtnReproducirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnReproducir.setEnabled(false);
			btnParar.setEnabled(true);
			GestorCancion gcan = new GestorCancion();
			String cancion = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(cancion, "-");
			Cancion canc = new Cancion(Integer.parseInt(token.nextToken()));
			try {
				canc=gcan.obtenerCancion(canc);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			lblEscuchando.setText("Reproduciendo "+canc.getTitulo()+" - "+canc.getAutor());
		}
	}
	private class BtnPararActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lblEscuchando.setText("");
			btnParar.setEnabled(false);
			btnReproducir.setEnabled(true);
		}
	}
}