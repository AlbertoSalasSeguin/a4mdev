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
	private final DAOCancion dcan;

	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;

	public Cancion(final int idCancionCons) {
		this.idCancion = idCancionCons;
		this.dcan = new DAOCancion();
	}

	public Cancion(final String tituloCons) {
		this.titulo = tituloCons;
		this.dcan = new DAOCancion();
	}

	public Cancion(final String tituloCons, final int idCancionCons, final String autorCons, final int albumCons, final float precioCons) {

		this.titulo = tituloCons;
		this.idCancion = idCancionCons;
		this.autor = autorCons;
		this.album = albumCons;
		this.precio = precioCons;
		this.dcan = new DAOCancion();
	}

	public Cancion() {
		this.dcan = new DAOCancion();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String newTitulo) {
		this.titulo = newTitulo;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(final int newIdCancion) {
		this.idCancion = newIdCancion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(final String newAutor) {
		this.autor = newAutor;
	}

	public int getAlbum() {
		return album;
	}

	public void setAlbum(final int newAlbum) {
		this.album = newAlbum;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(final float newPrecio) {
		this.precio = newPrecio;
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
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(THREE), resultSet.getInt(FOUR), resultSet.getFloat(FIVE));
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
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(THREE), resultSet.getInt(FOUR), resultSet.getFloat(FIVE));
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
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(THREE), resultSet.getInt(FOUR), resultSet.getFloat(FIVE));
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
				cancion = new Cancion(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(THREE), resultSet.getInt(FOUR), resultSet.getFloat(FIVE));

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
