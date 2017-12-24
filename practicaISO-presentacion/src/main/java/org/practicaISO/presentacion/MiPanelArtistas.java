package org.practicaISO.presentacion;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;

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
	private JButton btnActualizarLista;

	/**
	 * Create the panel.
	 */
	public MiPanelArtistas(Cliente client) {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		{
			label = new JLabel("");
			label.setIcon(
					new ImageIcon(MiPanelArtistas.class.getResource("/org/practicaISO/presentacion/IconoSpotify.png")));
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
			actualizarModelo(client);
		}

		{
			btnParar = new JButton("Parar");
			btnParar.setIcon(
					new ImageIcon(MiPanelArtistas.class.getResource("/org/practicaISO/presentacion/IconoParar.png")));
			btnParar.addActionListener(new BtnPararActionListener());
			btnParar.setEnabled(false);
			btnParar.setBounds(646, 250, 176, 47);
			add(btnParar);
		}
		{
			btnReproducir = new JButton("Reproducir album");
			btnReproducir.setIcon(
					new ImageIcon(MiPanelArtistas.class.getResource("/org/practicaISO/presentacion/IconoPlay.png")));
			btnReproducir.addActionListener(new BtnReproducirActionListener());
			btnReproducir.setEnabled(false);
			btnReproducir.setBounds(646, 168, 186, 47);
			add(btnReproducir);
		}
		{
			lblReproduciendo = new JLabel("");
			lblReproduciendo.setForeground(Color.WHITE);
			lblReproduciendo.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblReproduciendo.setBounds(58, 434, 842, 38);
			add(lblReproduciendo);
		}
		{
			btnActualizarLista = new JButton("Actualizar Lista");
			btnActualizarLista.addActionListener(new BtnActualizarListaActionListener2());
			btnActualizarLista.setIcon(new ImageIcon(
					MiPanelArtistas.class.getResource("/org/practicaISO/presentacion/IconoActualizar.png")));
			btnActualizarLista.setBounds(646, 327, 202, 47);
			add(btnActualizarLista);
		}

	}

	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnReproducir.setEnabled(true);
		}
	}

	public void actualizarModelo(Cliente client) {
		ArrayList<Cancion> ac = null;
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		DefaultListModel<String> modelolimpio = new DefaultListModel<String>();
		try {
			ac = client.obtenerCanciones();
		} catch (Exception e) {
			System.out.println("Error al obtener canciones del usuario");
		}
		if (ac != null) {
			for (Cancion canc : ac) {
				try {
					canc = canc.obtenerCancionId();
					modelo.addElement(canc.getAutor());

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			// para no repetir albumes
			for (int ii = 0; ii < modelo.size(); ii++) {
				for (int jj = 0; jj < modelo.size(); jj++) {
					if (!modelo.get(ii).equals(modelo.get(jj))) {
						if (!modelolimpio.contains(modelo.get(jj))) {
							modelolimpio.addElement(modelo.get(jj));
						}
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "No hay artistas que mostrar");
		}
		list.setModel(modelolimpio);
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
			String autor = list.getSelectedValue().toString();
			ArrayList<Cancion> canciones = new ArrayList<Cancion>();
			Cancion canc = new Cancion();
			canc.setAutor(autor);
			try {
				canciones = canc.obtenerCancionesAutor();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			lblReproduciendo.setText("Reproduciendo lista aleatoria " + canciones.get(0).getTitulo() + " - "
					+ canciones.get(0).getAutor());
		}
	}

	private class BtnActualizarListaActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Cliente client = new Cliente(lblNick.getText());
			actualizarModelo(client);
			btnReproducir.setEnabled(false);
			btnParar.setEnabled(false);
		}
	}
}
