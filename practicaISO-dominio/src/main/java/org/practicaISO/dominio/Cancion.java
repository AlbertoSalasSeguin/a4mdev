package org.practicaISO.dominio;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.practicaISO.persistencia.DAOCancion;

public class Cancion {

	private String titulo;
	private int idCancion;
	private String autor;
	private int album;
	private float precio;
	final private DAOCancion dcan;

	public Cancion(final int idCancion) {
		this.idCancion = idCancion;
		this.dcan = new DAOCancion();
	}

	public Cancion(final String titulo) {
		this.titulo = titulo;
		this.dcan = new DAOCancion();
	}

	public Cancion(final String titulo, final int idCancion, final String autor, final int album, final float precio) {

		this.titulo = titulo;
		this.idCancion = idCancion;
		this.autor = autor;
		this.album = album;
		this.precio = precio;
		this.dcan = new DAOCancion();
	}

	public Cancion() {
		this.dcan = new DAOCancion();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(final int idCancion) {
		this.idCancion = idCancion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(final String autor) {
		this.autor = autor;
	}

	public int getAlbum() {
		return album;
	}

	public void setAlbum(final int album) {
		this.album = album;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(final float precio) {
		this.precio = precio;
	}

	public String toStringCompra() {

		return this.getIdCancion() + "-" + this.getTitulo() + " - " + this.getAutor() + " - " + this.getPrecio() + "€";
	}

	public String toString() {

		return this.getIdCancion() + "-" + this.getTitulo() + " - " + this.getAutor();
	}

	public ArrayList<Cancion> leerCanciones() {
		final ResultSet resultSet = dcan.leerCancionesDAO();
		final ArrayList<Cancion> arrayCanciones = new ArrayList<Cancion>();
		Cancion cancion = null;
		try {
			while (resultSet.next()) {
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5));
				arrayCanciones.add(cancion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayCanciones;
	}

	public ArrayList<Cancion> obtenerCancionesAutor() {
		final ResultSet resultSet = dcan.obtenerCancionesAutorDAO(autor);
		final ArrayList<Cancion> arrayCanciones = new ArrayList<Cancion>();
		Cancion cancion = null;
		try {
			while (resultSet.next()) {
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5));
				arrayCanciones.add(cancion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayCanciones;
	}

	public ArrayList<String> obtenerCancionesUsuario(final String nick) {
		final ArrayList<String> ids = new ArrayList<String>();
		final ResultSet resultSet = dcan.obtenerIdsCancionesDAO(nick);
		try {
			while (resultSet.next()) {
				ids.add(String.valueOf(resultSet.getInt(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public Cancion obtenerCancionId() {
		final ResultSet resultSet = dcan.obtenerCancionIdDAO(idCancion);
		Cancion cancion = null;
		try {
			while (resultSet.next()) {
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancion;
	}

	public Cancion obtenerCancionTitulo() {
		final ResultSet resultSet = dcan.obtenerCancionTituloDAO(titulo);
		Cancion cancion = null;
		try {
			while (resultSet.next()) {
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancion;
	}

	public void insertarCancion() {
		dcan.insertarCancion(titulo, idCancion, autor, album, precio);

	}

	public void actualizarCancion() {
		dcan.actualizarCancionDAO(titulo, idCancion, autor, album, precio);
	}

	public void eliminarCancion() {
		dcan.eliminarCancionDAO(idCancion);
	}
}
