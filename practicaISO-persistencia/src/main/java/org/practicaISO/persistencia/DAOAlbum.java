package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOAlbum {

	private Connection con;
	private PreparedStatement pst;

	public ResultSet obtenerAlbumDAO(int idalbum) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select * from tb_album where idalbum = ? order by idalbum";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idalbum);
			rs = pst.executeQuery();
		} catch (Exception e) {
			System.out.println("Error al obtener álbum");
		}
		return rs;
	}

	public void insertarAlbumDAO(int idAlbum, String nombre) {
		try {

			con = Agente.getConexion();
			con.createStatement();
			String sql = "insert into tb_album values(?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idAlbum);
			pst.setString(2, nombre);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar album en la base de datos");
		}

	}

	public void actualizarAlbumDAO(int idAlbum, String nombre) {
		try {
			con = Agente.getConexion();
			con.createStatement();
			String sql = "update tb_album set idalbum = " + idAlbum + ", nombre = '" + nombre + "'";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar álbum en la base de datos");
		}
	}

	public ResultSet obtenerIdsCancionesAlbumDAO(int idAlbum) {
		ResultSet rs = null;
		try {
			con = Agente.getConexion();
			String sql = "select idcancion from tb_cancion where album = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idAlbum);
			rs = pst.executeQuery();

		} catch (Exception e) {
			System.out.println("Error al obtener canciones");
		}
		return rs;

	}

	public void eliminarAlbumDAO(int idalbum) {
		try {
			con = Agente.getConexion();
			con.createStatement();
			String sql = "delete from tb_album where idalbum = " + idalbum + "";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar álbum en la base de datos");
		}
	}
}
