package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOCliente {
	private Connection con;
	private PreparedStatement pst;
	private boolean realizado;

	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;

	public ResultSet logearClienteDAO(final String nick, final String pass) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select*from tb_cliente where nick = ? and pass = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setString(2, pass);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return resultSet;
	}

	public ResultSet obtenerClienteDAO(final String nick) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select*from tb_cliente where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return resultSet;
	}

	public ResultSet obtenerCancionesDAO(final String nick) {

		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select canciones from tb_playlist where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al cargar playlist del usuario");
		}
		return resultSet;

	}

	public boolean insertarClienteDAO(final String nick, final String pass, final String email, final String nombre, final String apellidos,
			final String suscripcion, final String rol) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "insert into tb_cliente values(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setString(2, pass);
			pst.setString(THREE, email);
			pst.setString(FOUR, nombre);
			pst.setString(FIVE, apellidos);
			pst.setString(SIX, suscripcion);
			pst.setString(SEVEN, rol);
			pst.executeUpdate();
		} catch (Exception e) {
			realizado = false;
			System.out.println("Error al insertar cliente en la base de datos");
		}

		return realizado;
	}

	public boolean actualizarClienteDAO(final String pass, final String email, final String nombre, final String apellidos, final String suscripcion,
			final String rol, final String nick) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "update tb_cliente set pass = ?, email = ?, nombre = ?, apellidos = ?, suscripcion = ?, rol = ? where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, pass);
			pst.setString(2, email);
			pst.setString(THREE, nombre);
			pst.setString(FOUR, apellidos);
			pst.setString(FIVE, suscripcion);
			pst.setString(SIX, rol);
			pst.setString(SEVEN, nick);
			pst.executeUpdate();
		} catch (Exception e) {
			realizado = false;
			System.out.println("Error al actualizar cliente en la base de datos");
		}
		return realizado;
	}

	public boolean comprarCancionDAO(final String nick, final int idcancion) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "insert into tb_playlist values(?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setInt(2, idcancion);
			pst.executeUpdate();
		} catch (Exception e) {
			realizado = false;
			System.out.println("Error al comprar canción");
		}
		return realizado;


	}

	public boolean eliminarClienteDAO(final String nick) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "delete from tb_cliente where nick = '" + nick + "'";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			realizado = false;
			System.out.println("Error al eliminar cliente en la base de datos");
		}
		return realizado;
	}
}