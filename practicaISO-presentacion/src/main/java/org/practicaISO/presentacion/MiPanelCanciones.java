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

public class MiPanelCanciones extends JPanel {
	private JList list;
	private JButton btnComprarCancin;
	private JLabel lblNick;

	/**
	 * Create the panel.
	 * @param c 
	 */
	public MiPanelCanciones(Cliente client) {
		setLayout(null);
		{
			list = new JList();
			list.setBackground(new Color(105, 105, 105));
			list.addListSelectionListener(new ListListSelectionListener());
			list.setBounds(0, 44, 387, 372);
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
					modelo.addElement(canc.toString());
				}
			}else {
				JOptionPane.showMessageDialog(this, "No hay canciones que mostrar");
			}
			list.setModel(modelo);
		
		}
		{
			btnComprarCancin = new JButton("Comprar canción");
			btnComprarCancin.addActionListener(new BtnComprarCancinActionListener());
			btnComprarCancin.setEnabled(false);
			btnComprarCancin.setBounds(414, 46, 137, 60);
			add(btnComprarCancin);
		}
		{
			lblNick = new JLabel(client.getNick());
			lblNick.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNick.setBounds(12, 13, 112, 18);
			add(lblNick);
		}

	}
	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnComprarCancin.setEnabled(true);

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
}
