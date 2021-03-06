package org.practicaISO.dominio;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.practicaISO.persistencia.DAOAlbum;

public class Album {

	private int idalbum;
	private String nombre;
	private DAOAlbum daoalbum;

	
	public Album(int idalbum, String nombre) {
		this.idalbum = idalbum;
		this.nombre = nombre;
		this.daoalbum = new DAOAlbum();
	}

	
	public Album(int idalbum) {
		this.idalbum = idalbum;
		this.daoalbum = new DAOAlbum();
	}

	public int getIdalbum() {
		return idalbum;
	}

	public void setIdalbum(int idalbum) {
		this.idalbum = idalbum;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Album obtenerAlbum() {
		ResultSet rs = daoalbum.obtenerAlbumDAO(idalbum);
		Album album = null;
		try {
			while (rs.next()) {
				album = new Album(rs.getInt(1), rs.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return album;
	}

	public ArrayList<String> obtenerIdsCancionesAlbum() {
		ArrayList<String> ids = new ArrayList<String>();
		ResultSet rs = daoalbum.obtenerIdsCancionesAlbumDAO(idalbum);
		try {
			while (rs.next()) {
				ids.add(String.valueOf(rs.getInt(1)));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public void insertarAlbum() {
		daoalbum.insertarAlbumDAO(idalbum, nombre);
	}

	public void actualizarAlbum() {
		daoalbum.actualizarAlbumDAO(idalbum, nombre);
	}

	public void eliminarAlbum() {
		daoalbum.eliminarAlbumDAO(idalbum);
	}

	public String toString() {
		return this.getIdalbum() + "-" + this.getNombre();
	}

}
