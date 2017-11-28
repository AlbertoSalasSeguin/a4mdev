package org.practicaISO.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;
;

public class GestorCancion {
	private Cancion cancion;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;

	
	public  ArrayList leerCanciones () {
		ArrayList <Cancion> ac= new ArrayList<Cancion>();
		try {
			con=MySQLConexion.getConexion();
			String sql = "select*from tb_cancion order by idcancion";
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();

			while(rs.next()) {
				cancion= new Cancion (rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4),
						rs.getDouble(5));
				ac.add(cancion);
			}
		}catch(Exception e) {
			System.out.println("Error al obtener lista de canciones");
		}

		return ac;
	}

	
	public ArrayList<String> obtenerIds (Cliente client) {
		ArrayList<String> ids = new ArrayList<String>();
		try {
			con=MySQLConexion.getConexion();
			String sql = "select canciones from tb_playlist where nick = ?";
			pst=con.prepareStatement(sql);
			pst.setString(1, client.getNick());
			rs=pst.executeQuery();

			while(rs.next()) {
				ids.add(String.valueOf(rs.getInt(1)));

			}
		} catch (Exception e) {
			System.out.println("Error al obtener canciones");
		}
		return ids;
		
	}
	
	
	public Cancion obtenerCancion(Cancion can) {

		try {
			con=MySQLConexion.getConexion();
			String sql = "select*from tb_cancion where idcancion = ?";
			pst=con.prepareStatement(sql);
			pst.setInt(1, can.getIdCancion());
			rs=pst.executeQuery();

			while(rs.next()) {
				cancion= new Cancion (rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4),
						rs.getDouble(5));

			}
		} catch (Exception e) {
			System.out.println("Error al obtener la cancion");
		}

		return cancion;
	}

	public void insertarCancion(Cancion can) {
		try {

			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "insert into tb_cancion values(?,?,?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1, can.getTitulo());
			pst.setInt(2, can.getIdCancion());
			pst.setString(3, can.getAutor());
			pst.setInt(4, can.getAlbum());
			pst.setDouble(5, can.getPrecio());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar canción en la base de datos");
		}

	}

	public void actualizarCancion(Cancion can) {
		try {
			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "update tb_cancion set titulo = '"+can.getTitulo()+"', idcancion = "+can.getIdCancion()+", autor = '"+can.getAutor()+"',"
					+ " album = "+can.getAlbum()+", precio = "+can.getPrecio()+"";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al actualizar cancion en la base de datos");
		}
	}

	public void eliminarCancion(Cancion can) {
		try {
			con=MySQLConexion.getConexion();
			st=con.createStatement();
			String sql = "delete from tb_cancion where idcancion = "+can.getIdCancion()+"";
			pst=con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al eliminar cancion en la base de datos");
		}	
	}
}
