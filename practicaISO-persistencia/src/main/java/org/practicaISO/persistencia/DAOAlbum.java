package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOAlbum {

	private Connection con;
	private PreparedStatement pst;
	private boolean realizado;


	public ResultSet obtenerAlbumDAO(final int idalbum) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select * from tb_album where idalbum = ? order by idalbum";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idalbum);
			resultSet = pst.executeQuery();
		} catch (Exception e) {
			System.out.println("Error al obtener álbum");
		}
		return resultSet;
	}

	public boolean insertarAlbumDAO(final int idAlbum, final String nombre) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "insert into tb_album values(?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idAlbum);
			pst.setString(2, nombre);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar album en la base de datos");
			realizado = false;
		}
		return realizado;
	}

	public boolean actualizarAlbumDAO(final int idAlbum, final String nombre) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "update tb_album set idalbum = " + idAlbum + ", nombre = '" + nombre + "'";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar álbum en la base de datos");
			realizado = false;
		}
		return realizado;
	}

	public ResultSet obtenerIdsCancionesAlbumDAO(final int idAlbum) {
		ResultSet resultSet = null;
		try {
			con = Agente.getConexion();
			final String sql = "select idcancion from tb_cancion where album = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idAlbum);
			resultSet = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener canciones");
		}
		return resultSet;

	}

	public boolean eliminarAlbumDAO(final int idalbum) {
		try {
			realizado = true;
			con = Agente.getConexion();
			con.createStatement();
			final String sql = "delete from tb_album where idalbum = " + idalbum + "";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar álbum en la base de datos");
			realizado = false;
		}
		return realizado;
	}
}