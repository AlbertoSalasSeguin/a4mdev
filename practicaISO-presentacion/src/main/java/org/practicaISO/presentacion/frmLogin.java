package org.practicaISO.presentacion;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.GestorCliente;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class frmLogin {

	private JFrame frmLogin;
	private JLabel lblIcon;
	private JLabel label;
	private JLabel lblNick;
	private JLabel lblPassword;
	private JTextField txtNick;
	private JPasswordField pwdPass;
	private JCheckBox chckbxMostrarContrasea;
	private JButton btnEntrar;
	private JLabel lblanNoTienes;
	private JLabel lblRegistrarme;
	private JLabel lblErrornick;
	private JLabel lblErrorpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin window = new frmLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setForeground(new Color(72, 209, 204));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setBackground(Color.DARK_GRAY);
		frmLogin.setBounds(100, 100, 637, 515);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		{
			lblIcon = new JLabel("");
			lblIcon.setIcon(new ImageIcon(frmLogin.class.getResource("/org/practicaISO/presentacion/Spotify_icon-icons.com_66783.png")));
			lblIcon.setBounds(267, 35, 106, 120);
			frmLogin.getContentPane().add(lblIcon);
		}
		{
			label = new JLabel("Spotify");
			label.setForeground(new Color(255, 255, 255));
			label.setFont(new Font("Tahoma", Font.BOLD, 24));
			label.setBounds(267, 150, 106, 41);
			frmLogin.getContentPane().add(label);
		}
		{
			lblNick = new JLabel("Nick");
			lblNick.setForeground(new Color(255, 255, 255));
			lblNick.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNick.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNick.setBounds(120, 207, 77, 27);
			frmLogin.getContentPane().add(lblNick);
		}
		{
			lblPassword = new JLabel("Password");
			lblPassword.setForeground(new Color(255, 255, 255));
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPassword.setBounds(110, 253, 87, 26);
			frmLogin.getContentPane().add(lblPassword);
		}
		{
			txtNick = new JTextField();
			txtNick.addKeyListener(new TxtNickKeyListener());
			txtNick.setColumns(10);
			txtNick.setBounds(232, 204, 188, 35);
			frmLogin.getContentPane().add(txtNick);
		}
		{
			pwdPass = new JPasswordField();
			pwdPass.addKeyListener(new PwdPassKeyListener());
			pwdPass.setBounds(232, 250, 188, 35);
			frmLogin.getContentPane().add(pwdPass);
		}
		{
			chckbxMostrarContrasea = new JCheckBox("Mostrar Contraseña");
			chckbxMostrarContrasea.setForeground(new Color(255, 255, 255));
			chckbxMostrarContrasea.setBackground(Color.DARK_GRAY);
			chckbxMostrarContrasea.addMouseListener(new ChckbxMostrarContraseaMouseListener());
			chckbxMostrarContrasea.setBounds(232, 294, 160, 25);
			frmLogin.getContentPane().add(chckbxMostrarContrasea);
		}
		{
			btnEntrar = new JButton("Entrar");
			btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnEntrar.setForeground(new Color(128, 128, 128));
			btnEntrar.setBackground(new Color(0, 204, 102));
			btnEntrar.addActionListener(new BtnEntrarActionListener());
			btnEntrar.setBounds(232, 344, 188, 35);
			frmLogin.getContentPane().add(btnEntrar);
		}
		{
			lblanNoTienes = new JLabel("¿Aún no tienes cuenta?");
			lblanNoTienes.setForeground(new Color(255, 255, 255));
			lblanNoTienes.setIcon(new ImageIcon(frmLogin.class.getResource("/org/practicaISO/presentacion/info_4908.png")));
			lblanNoTienes.setBounds(159, 423, 178, 35);
			frmLogin.getContentPane().add(lblanNoTienes);
		}
		{
			lblRegistrarme = new JLabel("Registrarme.");
			lblRegistrarme.setForeground(new Color(255, 255, 255));
			lblRegistrarme.addMouseListener(new LblRegistrarmeMouseListener());
			lblRegistrarme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblRegistrarme.setBounds(339, 429, 106, 21);
			frmLogin.getContentPane().add(lblRegistrarme);
		}
		{
			lblErrornick = new JLabel("");
			lblErrornick.setVisible(false);
			lblErrornick.setIcon(new ImageIcon(frmLogin.class.getResource("/org/practicaISO/presentacion/exclamation_icon-icons.com_62226.png")));
			lblErrornick.setBounds(432, 199, 56, 35);
			frmLogin.getContentPane().add(lblErrornick);
		}
		{
			lblErrorpass = new JLabel("");
			lblErrorpass.setVisible(false);
			lblErrorpass.setIcon(new ImageIcon(frmLogin.class.getResource("/org/practicaISO/presentacion/exclamation_icon-icons.com_62226.png")));
			lblErrorpass.setBounds(432, 253, 56, 35);
			frmLogin.getContentPane().add(lblErrorpass);
		}
	}
	
	public void entrar() {
		String nick=txtNick.getText();
		String password=String.valueOf(pwdPass.getPassword());
		GestorCliente gc = new GestorCliente();
		Cliente client= new Cliente();
		client.setNick(nick);
		client.setPass(password);
		Cliente c=gc.logearCliente(client);
		
		if(c!=null) {
			JOptionPane.showMessageDialog(frmLogin.getContentPane(), "Bienvenido "+c.getNombre());
			frmLogin.dispose();
			frmInterfaz fi = new frmInterfaz(c);
			fi.setVisible(true);
			
		
			
		}else if(txtNick.getText().equals("")){
			
			lblErrornick.setVisible(true);
			txtNick.setCaretColor(Color.RED);
			txtNick.requestFocus();
			
		}else if(pwdPass.getText().equals("")) {
			
			lblErrorpass.setVisible(true);
			pwdPass.setCaretColor(Color.RED);
			pwdPass.requestFocus();
		}else {
			JOptionPane.showMessageDialog(frmLogin.getContentPane(), "Datos inválidos", "Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private class BtnEntrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			entrar();
			
		}
	}
	private class TxtNickKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			lblErrornick.setVisible(false);
		}
	}
	private class PwdPassKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			lblErrorpass.setVisible(false);
		}
	}
	private class ChckbxMostrarContraseaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(chckbxMostrarContrasea.isSelected()) {
				pwdPass.setEchoChar((char)0);
			}else {
				pwdPass.setEchoChar('*');
			}
		}
	}
	
	public void mostrar () {
		frmLogin.setVisible(true);
	}
	
	private class LblRegistrarmeMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			frmLogin.dispose();
			frmRegistro fr = new frmRegistro();
			fr.setVisible(true);
		}
	}
}
