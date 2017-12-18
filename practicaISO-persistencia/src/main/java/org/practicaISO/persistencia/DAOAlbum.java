package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.practicaISO.dominio.Album;
import org.practicaISO.dominio.Cancion;


public class DAOAlbum {
	private Album album;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;

	public Album obtenerAlbum(Cancion canc) {

		try {
			con=MySQLConexion.getConexion();
			String sql = "select * from tb_album where idalbum = ? order by idalbum";
			pst=con.prepareStatement(sql);
			pst.setInt(1, canc.getAlbum());
			rs=pst.executeQuery();

			while(rs.next()) {
				album= new Album (rs.getInt(1),rs.getString(2));

			}
		} catch (Exception e) {
			System.out.println("Error al obtener álbum");
		}

		return album;
	}

	public void insertarAlbum(Album alb) {
		try {

			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "insert into tb_album values(?,?)";
			pst=con.prepareStatement(sql);
			pst.setInt(1, alb.getIdalbum());
			pst.setString(2, alb.getNombre());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar album en la base de datos");
		}

	}

	public void actualizarAlbum(Album alb) {
		try {
			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "update tb_album set idalbum = "+alb.getIdalbum()+", nombre = '"+alb.getNombre()+"'";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar álbum en la base de datos");
		}
	}
	
	public void eliminarAlbum(Album alb) {
		try {
			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "delete from tb_album where idalbum = "+alb.getIdalbum()+"";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar álbum en la base de datos");
		}	
	}
}
