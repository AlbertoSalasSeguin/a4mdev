package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOCancion {
	private Connection con;
	private PreparedStatement pst;
	private boolean realizado;

	public ResultSet leerCancionesDAO() {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select*from tb_cancion order by idcancion";
			pst = con.prepareStatement(sql);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener lista de canciones");
		}

		return resultSet;
	}

	public ResultSet obtenerCancionesAutorDAO(final String autor) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select*from tb_cancion where autor = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, autor);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener lista de canciones");
		}

		return resultSet;
	}

	public ResultSet obtenerIdsCancionesDAO(final String nick) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select canciones from tb_playlist where nick = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener canciones");
		}
		return resultSet;

	}

	public ResultSet obtenerCancionIdDAO(final int idcancion) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select*from tb_cancion where idcancion = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idcancion);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener la cancion");
		}

		return resultSet;
	}

	public ResultSet obtenerCancionTituloDAO(final String titulo) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select*from tb_cancion where titulo = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, titulo);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener la cancion");
		}

		return resultSet;
	}

	public boolean insertarCancion(final String titulo, final int idcancion, final String autor, final int idalbum, final float precio) {
		try {
			realizado=true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "insert into tb_cancion values(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, titulo);
			pst.setInt(2, idcancion);
			pst.setString(3, autor);
			pst.setInt(4, idalbum);
			pst.setFloat(5, precio);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar canción en la base de datos");
			realizado= false;
		}
		return realizado;

	}

	public boolean actualizarCancionDAO(final String titulo, final int idcancion, final String autor, final int idalbum, final float precio) {
		try {
			realizado= true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "update tb_cancion set titulo = '" + titulo + "', autor = '" + autor + "'," + " album = "
					+ idalbum + ", precio = " + precio + " where idcancion = " + idcancion;
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cancion en la base de datos");
			realizado=false;
		}
		return realizado;
	}

	public boolean eliminarCancionDAO(final int idcancion) {
		try {
			realizado= true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "delete from tb_cancion where idcancion = " + idcancion;
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar cancion en la base de datos");
			realizado=false;
		}
		return realizado;
	}
}
