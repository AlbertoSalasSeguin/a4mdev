package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOCancion {
	private Connection con;
	private PreparedStatement pst;

	public ResultSet leerCancionesDAO() {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select*from tb_cancion order by idcancion";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener lista de canciones");
		}

		return rs;
	}

	public ResultSet obtenerCancionesAutorDAO(String autor) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select*from tb_cancion where autor = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, autor);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener lista de canciones");
		}

		return rs;
	}

	public ResultSet obtenerIdsCancionesDAO(String nick) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select canciones from tb_playlist where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener canciones");
		}
		return rs;

	}

	public ResultSet obtenerCancionIdDAO(int idcancion) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select*from tb_cancion where idcancion = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idcancion);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener la cancion");
		}

		return rs;
	}

	public ResultSet obtenerCancionTituloDAO(String titulo) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select*from tb_cancion where titulo = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, titulo);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener la cancion");
		}

		return rs;
	}

	public void insertarCancion(String titulo, int idcancion, String autor, int idalbum, float precio) {
		try {

			con = Agente.getConexion();
			con.createStatement();
			String sql = "insert into tb_cancion values(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, titulo);
			pst.setInt(2, idcancion);
			pst.setString(3, autor);
			pst.setInt(4, idalbum);
			pst.setFloat(5, precio);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar canción en la base de datos");
		}

	}

	public void actualizarCancionDAO(String titulo, int idcancion, String autor, int idalbum, float precio) {
		try {
			con = Agente.getConexion();
			con.createStatement();
			String sql = "update tb_cancion set titulo = '" + titulo + "', autor = '" + autor + "'," + " album = "
					+ idalbum + ", precio = " + precio + " where idcancion = " + idcancion;
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cancion en la base de datos");
		}
	}

	public void eliminarCancionDAO(int idcancion) {
		try {
			con = Agente.getConexion();
			con.createStatement();
			String sql = "delete from tb_cancion where idcancion = " + idcancion + "";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar cancion en la base de datos");
		}
	}
}
