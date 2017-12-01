package org.practicaISO.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.GestorCancion;
import org.practicaISO.persistencia.GestorCliente;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class MiGestorCancion extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel lblArtista;
	private JLabel lblTitulo;
	private JLabel lblPrecio;
	private JLabel lbllbum;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtAlbum;
	private JTextField txtArtista;
	private JLabel lblGestorDeAdministracin;
	private JButton btnActualizarCancin;
	private JButton btnEliminarCancin;
	private JLabel lblIdDeLa;
	private JLabel lblId;


	/**
	 * Create the frame.
	 */
	public MiGestorCancion(Cancion canc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 553);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			label = new JLabel("");
			label.setIcon(new ImageIcon(MiGestorCancion.class.getResource("/org/practicaISO/presentacion/IconoSpotify.png")));
			label.setBounds(748, 0, 176, 127);
			contentPane.add(label);
		}
		{
			lblArtista = new JLabel("Artista");
			lblArtista.setHorizontalAlignment(SwingConstants.RIGHT);
			lblArtista.setForeground(Color.WHITE);
			lblArtista.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblArtista.setBounds(48, 287, 81, 17);
			contentPane.add(lblArtista);
		}
		{
			lblTitulo = new JLabel("Titulo");
			lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTitulo.setForeground(Color.WHITE);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitulo.setBounds(57, 238, 72, 17);
			contentPane.add(lblTitulo);
		}
		{
			lblPrecio = new JLabel("Precio");
			lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrecio.setForeground(Color.WHITE);
			lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPrecio.setBounds(73, 334, 56, 17);
			contentPane.add(lblPrecio);
		}
		{
			lbllbum = new JLabel("Álbum");
			lbllbum.setHorizontalAlignment(SwingConstants.RIGHT);
			lbllbum.setForeground(Color.WHITE);
			lbllbum.setFont(new Font("Tahoma", Font.BOLD, 14));
			lbllbum.setBounds(68, 386, 61, 17);
			contentPane.add(lbllbum);
		}
		{
			txtTitulo = new JTextField();
			txtTitulo.setText((String) null);
			txtTitulo.setColumns(10);
			txtTitulo.setBounds(175, 236, 116, 22);
			txtTitulo.setText(canc.getTitulo());
			contentPane.add(txtTitulo);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setText((String) null);
			txtPrecio.setColumns(10);
			txtPrecio.setBounds(175, 332, 116, 22);
			txtPrecio.setText(String.valueOf(canc.getPrecio()));
			contentPane.add(txtPrecio);
		}
		{
			txtAlbum = new JTextField();
			txtAlbum.setText((String) null);
			txtAlbum.setColumns(10);
			txtAlbum.setBounds(175, 384, 116, 22);
			txtAlbum.setText(String.valueOf(canc.getAlbum()));
			contentPane.add(txtAlbum);
		}
		{
			txtArtista = new JTextField();
			txtArtista.setText((String) null);
			txtArtista.setColumns(10);
			txtArtista.setBounds(175, 285, 116, 22);
			txtArtista.setText(canc.getAutor());
			contentPane.add(txtArtista);
		}
		{
			lblGestorDeAdministracin = new JLabel("Gestor de administración de canciones");
			lblGestorDeAdministracin.setForeground(Color.WHITE);
			lblGestorDeAdministracin.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblGestorDeAdministracin.setBounds(58, 26, 528, 58);
			contentPane.add(lblGestorDeAdministracin);
		}
		{
			btnActualizarCancin = new JButton("Actualizar canción");
			btnActualizarCancin.addActionListener(new BtnActualizarCancinActionListener());
			btnActualizarCancin.setBounds(439, 335, 165, 68);
			contentPane.add(btnActualizarCancin);
		}
		{
			btnEliminarCancin = new JButton("Eliminar canción");
			btnEliminarCancin.addActionListener(new BtnEliminarCancinActionListener());
			btnEliminarCancin.setBounds(729, 411, 165, 68);
			contentPane.add(btnEliminarCancin);
		}
		{
			lblIdDeLa = new JLabel("id de la cancion =");
			lblIdDeLa.setForeground(Color.WHITE);
			lblIdDeLa.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblIdDeLa.setBounds(57, 81, 245, 58);
			contentPane.add(lblIdDeLa);
		}
		{
			lblId = new JLabel(String.valueOf(canc.getIdCancion()));
			lblId.setPreferredSize(new Dimension(104, 20));
			lblId.setMinimumSize(new Dimension(104, 20));
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			lblId.setBounds(314, 97, 242, 38);
			contentPane.add(lblId);
		}
	}
	private class BtnEliminarCancinActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar esta canción? Esta acción no se puede deshacer."
					,"Ventana de confirmación",JOptionPane.YES_NO_OPTION);
			if (confirmado==JOptionPane.YES_OPTION) {
			Cancion canc = new Cancion(txtTitulo.getText());
			GestorCancion gc = new GestorCancion();
			
				try {
					canc=gc.obtenerCancionTitulo(canc);
					gc.eliminarCancion(canc);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}
	}
	private class BtnActualizarCancinActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres actualizar esta canción?"
					,"Ventana de confirmación",JOptionPane.YES_NO_OPTION);
			if (confirmado==JOptionPane.YES_OPTION) {
				GestorCancion gc = new GestorCancion();
				Cancion canc = new Cancion(txtTitulo.getText(),Integer.parseInt(lblId.getText()) , txtArtista.getText(), Integer.parseInt(txtAlbum.getText()),
						Float.parseFloat(txtPrecio.getText()));

				try {
					gc.actualizarCancion(canc);
					JOptionPane.showMessageDialog(null, "Canción actualizada con éxito");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			dispose();
		}
	}
}
