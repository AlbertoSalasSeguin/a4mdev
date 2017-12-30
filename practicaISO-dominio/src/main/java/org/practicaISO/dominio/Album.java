package org.practicaISO.dominio;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.practicaISO.persistencia.DAOAlbum;

public class Album {

	private int idalbum;
	private String nombre;
	private final DAOAlbum daoalbum;

	public Album(final int idalbumCons, final String nombreCons) {
		this.idalbum = idalbumCons;
		this.nombre = nombreCons;
		this.daoalbum = new DAOAlbum();
	}

	public Album(final int idalbumCons) {
		this.idalbum = idalbumCons;
		this.daoalbum = new DAOAlbum();
	}

	public int getIdalbum() {
		return idalbum;
	}

	public void setIdalbum(final int newIdalbum) {
		this.idalbum = newIdalbum;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String newNombre) {
		this.nombre = newNombre;
	}

	public Album obtenerAlbum() {
		final ResultSet resultSet = daoalbum.obtenerAlbumDAO(idalbum);
		Album album = null;
		try {
			while (resultSet.next()) {
				album = new Album(resultSet.getInt(1), resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return album;
	}

	public ArrayList<String> obtenerIdsCancionesAlbum() {
		final ArrayList<String> ids = new ArrayList<String>();
		final ResultSet resultSet = daoalbum.obtenerIdsCancionesAlbumDAO(idalbum);
		try {
			while (resultSet.next()) {
				ids.add(String.valueOf(resultSet.getInt(1)));
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
