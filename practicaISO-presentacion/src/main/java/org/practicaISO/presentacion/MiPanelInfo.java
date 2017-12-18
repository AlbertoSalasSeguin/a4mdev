package org.practicaISO.presentacion;

import javax.swing.JPanel;

import org.practicaISO.dominio.Cliente;
import org.practicaISO.persistencia.DAOCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class MiPanelInfo extends JPanel {
	private JLabel lblPass;
	private JLabel lblEmail;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JPasswordField pwdPass;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JLabel lblLblicon;
	private JLabel lblInformacinDeLa;
	private JButton btnActualizarMisDatos;
	private JButton btnAtrs;
	private JLabel lblLblnick;
	private JCheckBox chckbxActualizarAPremium;
	private JButton btnEliminarMiCuenta;
	private JButton btnCerrarSesin;

	/**
	 * Create the panel.
	 */
	public MiPanelInfo(Cliente c) {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		{
			lblPass = new JLabel("Contraseña");
			lblPass.setBounds(58, 324, 80, 17);
			lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPass.setForeground(Color.WHITE);
			lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));

			add(lblPass);
		}
		{
			lblEmail = new JLabel("Email");
			lblEmail.setBounds(102, 275, 36, 17);
			lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmail.setForeground(Color.WHITE);
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));

			add(lblEmail);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(82, 371, 56, 17);
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));

			add(lblNombre);
		}
		{
			lblApellidos = new JLabel("Apellidos");
			lblApellidos.setBounds(77, 423, 61, 17);
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setForeground(Color.WHITE);
			lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
			add(lblApellidos);
		}
		{
			pwdPass = new JPasswordField();
			pwdPass.setBounds(184, 322, 116, 22);
			pwdPass.setText(c.getPass());
			add(pwdPass);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(184, 273, 116, 22);
			txtEmail.setColumns(10);
			txtEmail.setText(c.getEmail());
			add(txtEmail);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(184, 369, 116, 22);
			txtNombre.setColumns(10);
			txtNombre.setText(c.getNombre());

			add(txtNombre);
		}
		{
			txtApellidos = new JTextField();
			txtApellidos.setBounds(184, 421, 116, 22);
			txtApellidos.setColumns(10);
			txtApellidos.setText(c.getApellidos());
			add(txtApellidos);
		}
		{
			lblLblicon = new JLabel("");
			lblLblicon.setIcon(
					new ImageIcon(MiPanelInfo.class.getResource("/org/practicaISO/presentacion/IconoSpotify.png")));
			lblLblicon.setBounds(768, 0, 176, 127);
			add(lblLblicon);
		}
		{
			lblInformacinDeLa = new JLabel("Información de la cuenta");
			lblInformacinDeLa.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblInformacinDeLa.setForeground(Color.WHITE);
			lblInformacinDeLa.setBounds(58, 26, 528, 58);
			add(lblInformacinDeLa);
		}
		{
			btnActualizarMisDatos = new JButton("Actualizar mis datos");
			btnActualizarMisDatos.setIcon(
					new ImageIcon(MiPanelInfo.class.getResource("/org/practicaISO/presentacion/IconoActualizar.png")));
			btnActualizarMisDatos.addActionListener(new BtnActualizarMisDatosActionListener());
			btnActualizarMisDatos.setBounds(406, 371, 164, 74);
			add(btnActualizarMisDatos);
		}
		{
			btnAtrs = new JButton("Restablecer");
			btnAtrs.addActionListener(new BtnAtrsActionListener());
			btnAtrs.setBounds(406, 277, 164, 46);
			add(btnAtrs);
		}
		{
			lblLblnick = new JLabel("");
			lblLblnick.setForeground(Color.WHITE);
			lblLblnick.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			lblLblnick.setBounds(58, 89, 242, 38);
			lblLblnick.setText(c.getNick());
			add(lblLblnick);
		}
		{
			chckbxActualizarAPremium = new JCheckBox("Actualizar a premium");
			chckbxActualizarAPremium.setForeground(Color.WHITE);
			chckbxActualizarAPremium.setBackground(Color.DARK_GRAY);
			chckbxActualizarAPremium.setBounds(184, 477, 201, 25);
			add(chckbxActualizarAPremium);
		}
		{
			btnEliminarMiCuenta = new JButton("Eliminar mi cuenta");
			btnEliminarMiCuenta.addActionListener(new BtnEliminarMiCuentaActionListener());
			btnEliminarMiCuenta.setBounds(738, 369, 156, 81);
			add(btnEliminarMiCuenta);
		}
		{
			btnCerrarSesin = new JButton("Cerrar sesión");
			btnCerrarSesin.addActionListener(new BtnCerrarSesinActionListener());
			btnCerrarSesin.setBounds(738, 275, 156, 51);
			add(btnCerrarSesin);
		}

	}

	private class BtnAtrsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Cliente c = new Cliente(lblLblnick.getText());
			DAOCliente gc = new DAOCliente();
			try {
				c = gc.obtenerCliente(c);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			pwdPass.setText(c.getPass());
			txtEmail.setText(c.getEmail());
			txtApellidos.setText(c.getApellidos());
			txtNombre.setText(c.getNombre());
		}
	}

	private class BtnActualizarMisDatosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DAOCliente gc = new DAOCliente();
			String suscripcion;
			if (chckbxActualizarAPremium.isSelected()) {
				suscripcion = "Premium";
			} else {
				suscripcion = "Normal";
			}
			Cliente client = new Cliente(lblLblnick.getText(), pwdPass.getText(), txtEmail.getText(),
					txtNombre.getText(), txtApellidos.getText(), suscripcion, "Usuario");
			try {
				gc.actualizarCliente(client);
				JOptionPane.showMessageDialog(null, "Tu cuenta se ha actualizado con éxito");
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	private class BtnEliminarMiCuentaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int confirmado = JOptionPane.showConfirmDialog(null,
					"¿Estás seguro de que quieres eliminar tu cuenta? Esta acción no se puede deshacer.",
					"Ventana de confirmación", JOptionPane.YES_NO_OPTION);
			if (confirmado == JOptionPane.YES_OPTION) {
				DAOCliente gc = new DAOCliente();
				Cliente client = new Cliente(lblLblnick.getText());
				try {

					gc.eliminarCliente(client);
					JOptionPane.showMessageDialog(null, "Tu cuenta se ha eliminado con éxito. Saliendo...");
					System.exit(0);

				} catch (Exception e2) {
					e2.printStackTrace();

				}
			}
		}
	}

	private class BtnCerrarSesinActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
