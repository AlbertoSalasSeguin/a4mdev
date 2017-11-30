package org.practicaISO.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.GestorCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class frmRegistro extends JFrame {

	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JLabel lblEmail;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JPasswordField pwdPass;
	private JTextField txtNick;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JButton btnRegistrarme;
	private JButton btnLimpiarCampos;
	private JButton btnCancelar;
	private JLabel lblLblimage_1;
	private JLabel lblLblimage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegistro frame = new frmRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmRegistro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 564);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(Color.WHITE);
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblUsuario.setBounds(207, 195, 101, 16);
			contentPane.add(lblUsuario);
		}
		{
			lblContrasea = new JLabel("Contraseña");
			lblContrasea.setForeground(Color.WHITE);
			lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
			lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblContrasea.setBounds(207, 234, 101, 16);
			contentPane.add(lblContrasea);
		}
		{
			lblEmail = new JLabel("Email");
			lblEmail.setForeground(Color.WHITE);
			lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblEmail.setBounds(207, 274, 101, 16);
			contentPane.add(lblEmail);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombre.setBounds(207, 320, 101, 16);
			contentPane.add(lblNombre);
		}
		{
			lblApellidos = new JLabel("Apellidos");
			lblApellidos.setForeground(Color.WHITE);
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblApellidos.setBounds(207, 362, 101, 16);
			contentPane.add(lblApellidos);
		}
		{
			pwdPass = new JPasswordField();
			pwdPass.setBounds(334, 229, 169, 28);
			contentPane.add(pwdPass);
		}
		{
			txtNick = new JTextField();
			txtNick.setColumns(10);
			txtNick.setBounds(334, 190, 169, 28);
			contentPane.add(txtNick);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(334, 272, 169, 28);
			contentPane.add(txtEmail);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(334, 315, 169, 28);
			contentPane.add(txtNombre);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(334, 357, 169, 28);
			contentPane.add(txtApellidos);
		}
		{
			btnRegistrarme = new JButton("Registrarme");
			btnRegistrarme.setForeground(Color.GRAY);
			btnRegistrarme.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnRegistrarme.setBackground(new Color(0, 204, 102));
			btnRegistrarme.addActionListener(new BtnRegistrarmeActionListener());
			btnRegistrarme.setBounds(334, 411, 169, 64);
			contentPane.add(btnRegistrarme);
		}
		{
			btnLimpiarCampos = new JButton("Limpiar campos");
			btnLimpiarCampos.addActionListener(new BtnLimpiarCamposActionListener());
			btnLimpiarCampos.setBounds(33, 347, 162, 49);
			contentPane.add(btnLimpiarCampos);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new BtnCancelarActionListener());
			btnCancelar.setBounds(33, 411, 162, 64);
			contentPane.add(btnCancelar);
		}
		{
			lblLblimage_1 = new JLabel("");
			lblLblimage_1.setIcon(new ImageIcon(frmRegistro.class.getResource("/org/practicaISO/presentacion/Spotify_icon-icons.com_66783.png")));
			lblLblimage_1.setBounds(126, 26, 124, 111);
			contentPane.add(lblLblimage_1);
		}
		{
			lblLblimage = new JLabel("");
			lblLblimage.setIcon(new ImageIcon(frmRegistro.class.getResource("/org/practicaISO/presentacion/user-green_25326.png")));
			lblLblimage.setBounds(33, 78, 188, 159);
			contentPane.add(lblLblimage);
		}
	}

	private class BtnRegistrarmeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Cliente client =null;
			if(!txtNick.getText().equals("") && !pwdPass.getText().equals("") && !txtEmail.getText().equals("")
					&& !txtNombre.getText().equals("") && !txtApellidos.getText().equals("")) {
						
						client= new Cliente(txtNick.getText(), pwdPass.getText(), txtEmail.getText(), txtNombre.getText(), txtApellidos.getText(),"Normal", "Usuario");
						GestorCliente gc= new GestorCliente();
			
						try {
							gc.insertarCliente(client);
						} catch (Exception e1) {
							System.out.println("No se ha podido registrar al cliente.");
						}
						JOptionPane.showMessageDialog(contentPane, "El usuario "+client.getNombre()+ " "+client.getApellidos()+" con usuario '"+client.getNick()+ "' "
								+ "se ha registrado correctamente.");
						dispose();
						frmInterfaz fi = new frmInterfaz(client);
						fi.setVisible(true);
		}
	}
}
	private class BtnCancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			frmLogin fl=new frmLogin();
			fl.mostrar();
			
		}
	}
	private class BtnLimpiarCamposActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		txtApellidos.setText("");
		txtEmail.setText("");
		txtNick.setText("");
		txtNombre.setText("");
		pwdPass.setText("");
		}
	}
}
