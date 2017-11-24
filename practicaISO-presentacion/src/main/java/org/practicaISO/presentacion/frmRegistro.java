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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblUsuario.setBounds(187, 108, 101, 16);
			contentPane.add(lblUsuario);
		}
		{
			lblContrasea = new JLabel("Contraseña");
			lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
			lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblContrasea.setBounds(187, 147, 101, 16);
			contentPane.add(lblContrasea);
		}
		{
			lblEmail = new JLabel("Email");
			lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblEmail.setBounds(187, 187, 101, 16);
			contentPane.add(lblEmail);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombre.setBounds(187, 233, 101, 16);
			contentPane.add(lblNombre);
		}
		{
			lblApellidos = new JLabel("Apellidos");
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblApellidos.setBounds(187, 275, 101, 16);
			contentPane.add(lblApellidos);
		}
		{
			pwdPass = new JPasswordField();
			pwdPass.setBounds(314, 142, 169, 28);
			contentPane.add(pwdPass);
		}
		{
			txtNick = new JTextField();
			txtNick.setColumns(10);
			txtNick.setBounds(314, 103, 169, 28);
			contentPane.add(txtNick);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(314, 185, 169, 28);
			contentPane.add(txtEmail);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(314, 228, 169, 28);
			contentPane.add(txtNombre);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(314, 270, 169, 28);
			contentPane.add(txtApellidos);
		}
		{
			btnRegistrarme = new JButton("Registrarme");
			btnRegistrarme.addActionListener(new BtnRegistrarmeActionListener());
			btnRegistrarme.setBounds(314, 332, 169, 64);
			contentPane.add(btnRegistrarme);
		}
		{
			btnLimpiarCampos = new JButton("Limpiar campos");
			btnLimpiarCampos.setBounds(42, 352, 162, 25);
			contentPane.add(btnLimpiarCampos);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(42, 387, 162, 28);
			contentPane.add(btnCancelar);
		}
	}

	private class BtnRegistrarmeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Cliente client =null;
			if(!txtNick.getText().equals("") && !pwdPass.getText().equals("") && !txtEmail.getText().equals("")
					&& !txtNombre.getText().equals("") && !txtApellidos.getText().equals("")) {
						
						client= new Cliente(txtNick.getText(), pwdPass.getText(), txtEmail.getText(), txtNombre.getText(), txtApellidos.getText(),"Normal");
						GestorCliente gc= new GestorCliente();
			
						try {
							gc.insertarCliente(client);
						} catch (Exception e1) {
							System.out.println("No se ha podido registrar al cliente.");
						}
						JOptionPane.showMessageDialog(contentPane, "El usuario "+client.getNombre()+ " "+client.getApellidos()+" con usuario '"+client.getNick()+ "' "
								+ "se ha registrado correctamente.");
						dispose();
		}
	}
}
}
