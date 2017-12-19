package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOCliente {
	private Connection con;
	private PreparedStatement pst;

	public ResultSet logearClienteDAO(String nick, String pass) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select*from tb_cliente where nick = ? and pass = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setString(2, pass);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return rs;
	}

	public ResultSet obtenerClienteDAO(String nick) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select*from tb_cliente where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener el cliente");
		}

		return rs;
	}

	public ResultSet obtenerCancionesDAO(String nick) {

		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select canciones from tb_playlist where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al cargar playlist del usuario");
		}
		return rs;

	}

	public void insertarClienteDAO(String nick, String pass, String email, String nombre, String apellidos,
			String suscripcion, String rol) {
		try {

			con = Agente.getConexion();
			con.createStatement();
			String sql = "insert into tb_cliente values(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setString(2, pass);
			pst.setString(3, email);
			pst.setString(4, nombre);
			pst.setString(5, apellidos);
			pst.setString(6, suscripcion);
			pst.setString(7, rol);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar cliente en la base de datos");
		}

	}

	public void actualizarClienteDAO(String pass, String email, String nombre, String apellidos, String suscripcion,
			String rol, String nick) {
		try {
			con = Agente.getConexion();
			con.createStatement();
			String sql = "update tb_cliente set pass = ?, email = ?, nombre = ?, apellidos = ?, suscripcion = ?, rol = ? where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, pass);
			pst.setString(2, email);
			pst.setString(3, nombre);
			pst.setString(4, apellidos);
			pst.setString(5, suscripcion);
			pst.setString(6, rol);
			pst.setString(7, nick);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cliente en la base de datos");
		}
	}

	public void comprarCancionDAO(String nick, int idcancion) {
		try {

			con = Agente.getConexion();
			con.createStatement();
			String sql = "insert into tb_playlist values(?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			pst.setInt(2, idcancion);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al comprar canción");
		}

	}

	public void eliminarClienteDAO(String nick) {
		try {
			con = Agente.getConexion();
			con.createStatement();
			String sql = "delete from tb_cliente where nick = '" + nick + "'";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar cliente en la base de datos");
		}
	}
}