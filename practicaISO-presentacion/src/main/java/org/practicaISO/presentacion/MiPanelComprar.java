package org.practicaISO.presentacion;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;

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
	private JButton btnEditarCancion;
	private JButton btnActualizarLista;

	public MiPanelComprar(final Cliente client) {
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
			actualizarModelo(client);

		}
		{
			btnComprarCancin = new JButton("Comprar canción");
			btnComprarCancin.setBounds(655, 425, 195, 50);
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
			btnParar.setIcon(
					new ImageIcon(MiPanelComprar.class.getResource("/org/practicaISO/presentacion/IconoParar.png")));
			btnParar.setVisible(false);
			btnParar.addActionListener(new BtnPararActionListener());
			btnParar.setEnabled(false);
			btnParar.setBounds(655, 228, 135, 50);
			add(btnParar);
		}
		{
			btnReproducir = new JButton("Reproducir");
			btnReproducir.setIcon(
					new ImageIcon(MiPanelComprar.class.getResource("/org/practicaISO/presentacion/IconoPlay.png")));
			btnReproducir.setVisible(false);
			btnReproducir.setEnabled(false);
			btnReproducir.addActionListener(new BtnReproducirActionListener());
			btnReproducir.setBounds(655, 165, 135, 50);
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
			lblicon.setIcon(
					new ImageIcon(MiPanelComprar.class.getResource("/org/practicaISO/presentacion/IconoSpotify.png")));
			lblicon.setBounds(768, 0, 176, 127);
			add(lblicon);
		}
		{
			btnEditarCancion = new JButton("Editar Canción");
			btnEditarCancion.addActionListener(new BtnEditarCancionActionListener());
			btnEditarCancion.setEnabled(false);
			btnEditarCancion.setVisible(false);
			btnEditarCancion.setBounds(655, 291, 135, 58);
			add(btnEditarCancion);
		}
		{
			btnActualizarLista = new JButton("Actualizar lista");
			btnActualizarLista.addActionListener(new BtnActualizarListaActionListener3());
			btnActualizarLista.setIcon(new ImageIcon(
					MiPanelComprar.class.getResource("/org/practicaISO/presentacion/IconoActualizar.png")));
			btnActualizarLista.setBounds(655, 362, 195, 50);
			add(btnActualizarLista);
		}

		if (client.getSuscripcion().equals("Premium")) {
			btnReproducir.setVisible(true);
			btnParar.setVisible(true);

		}
	}

	private void actualizarModelo(Cliente client) {
		Cancion canc = new Cancion();
		ArrayList<Cancion> ac = null;
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		try {
			ac = canc.leerCanciones();
		} catch (Exception e) {
			System.out.println("Error ");
		}
		if (ac != null) {
			for (Cancion ca : ac) {
				modelo.addElement(ca.toStringCompra());
			}
		} else {
			JOptionPane.showMessageDialog(this, "No hay canciones que mostrar");
		}
		list.setModel(modelo);

	}

	private class ListListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnComprarCancin.setEnabled(true);
			btnReproducir.setEnabled(true);
			Cliente client = new Cliente(lblNick.getText());
			try {
				client = client.obtenerCliente();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if (client.getSuscripcion().equals("Premium")) {
				btnReproducir.setVisible(true);
				btnParar.setVisible(true);
			}
			if (client.getRol().equals("Admin")) {
				btnEditarCancion.setVisible(true);
				btnEditarCancion.setEnabled(true);
			}
		}
	}

	private class BtnComprarCancinActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cancion = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(cancion, "-");
			Cancion canc = new Cancion(Integer.parseInt(token.nextToken()));
			Cliente client = new Cliente(lblNick.getText());
			try {
				canc = canc.obtenerCancionId();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			ArrayList<String> ids = canc.obtenerCancionesUsuario(client.getNick());
			if (esComprada(ids, canc)) {
				JOptionPane.showMessageDialog(null, "Esta canción ya ha sido adquirida");
			} else {
				int confirmado = JOptionPane.showConfirmDialog(null, "¿Deseas adquirir esta canción?",
						"Ventana de confirmación", JOptionPane.YES_NO_OPTION);
				if (confirmado == JOptionPane.YES_OPTION) {

					try {
						client.comprarCancion(canc.getIdCancion());
						JOptionPane.showMessageDialog(null,
								"Enhorabuena has comprado " + canc.getTitulo() + " de " + canc.getAutor());

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}
		}

		private boolean esComprada(final ArrayList<String> ids, final Cancion canc) {
			boolean comprado = false;
			for (int i = 0; i < ids.size(); i++) {
				if (canc.getIdCancion() == Integer.parseInt(ids.get(i).toString())) {
					comprado = true;
				}
			}
			return comprado;
		}
	}

	private class BtnReproducirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnReproducir.setEnabled(false);
			btnParar.setEnabled(true);
			String cancion = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(cancion, "-");
			Cancion canc = new Cancion(Integer.parseInt(token.nextToken()));
			try {
				canc = canc.obtenerCancionId();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			lblEscuchando.setText("Reproduciendo " + canc.getTitulo() + " - " + canc.getAutor());
		}
	}

	private class BtnPararActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lblEscuchando.setText("");
			btnParar.setEnabled(false);
			btnReproducir.setEnabled(true);
		}
	}

	private class BtnEditarCancionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cancion = list.getSelectedValue().toString();
			StringTokenizer token = new StringTokenizer(cancion, "-");
			Cancion canc = new Cancion(Integer.parseInt(token.nextToken()));
			try {
				canc = canc.obtenerCancionId();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			MiGestorCancion mgc = new MiGestorCancion(canc);
			mgc.setVisible(true);

		}
	}

	private class BtnActualizarListaActionListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Cliente client = new Cliente(lblNick.getText());
			actualizarModelo(client);
			btnComprarCancin.setEnabled(false);
			btnReproducir.setEnabled(false);
			btnParar.setEnabled(false);
			btnEditarCancion.setEnabled(false);
		}

	}

}