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
	private DAOCancion dcan;

	public Cancion(int idCancion) {
		this.idCancion = idCancion;
		this.dcan = new DAOCancion();
	}

	public Cancion(String titulo) {
		this.titulo = titulo;
		this.dcan = new DAOCancion();
	}

	public Cancion(String titulo, int idCancion, String autor, int album, float precio) {

		this.titulo = titulo;
		this.idCancion = idCancion;
		this.autor = autor;
		this.album = album;
		this.precio = precio;
		this.dcan = new DAOCancion();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAlbum() {
		return album;
	}

	public void setAlbum(int album) {
		this.album = album;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String toStringCompra() {

		return this.getIdCancion() + "-" + this.getTitulo() + " - " + this.getAutor() + " - " + this.getPrecio() + "€";
	}

	public String toString() {

		return this.getIdCancion() + "-" + this.getTitulo() + " - " + this.getAutor();
	}

	public ArrayList<Cancion> leerCanciones() {
		ResultSet rs = dcan.leerCancionesDAO();
		ArrayList<Cancion> ac = new ArrayList<Cancion>();
		Cancion cancion = null;
		try {
			while (rs.next()) {
				cancion = new Cancion(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getFloat(5));
				ac.add(cancion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public ArrayList<Cancion> obtenerCancionesAutor() {
		ResultSet rs = dcan.obtenerCancionesAutorDAO(autor);
		ArrayList<Cancion> ac = new ArrayList<Cancion>();
		Cancion cancion = null;
		try {
			while (rs.next()) {
				cancion = new Cancion(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getFloat(5));
				ac.add(cancion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public ArrayList<String> obtenerCanciones(String nick) {
		ArrayList<String> ids = new ArrayList<String>();
		ResultSet rs = dcan.obtenerIdsCancionesDAO(nick);
		try {
			while (rs.next()) {
				ids.add(String.valueOf(rs.getInt(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public ArrayList<String> obtenerIdsAlbum(int idalbum) {
		ArrayList<String> ids = new ArrayList<String>();
		ResultSet rs = dcan.obtenerIdsAlbumDAO(idalbum);
		try {
			while (rs.next()) {
				ids.add(String.valueOf(rs.getInt(1)));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public Cancion obtenerCancionId() {
		ResultSet rs = dcan.obtenerCancionIdDAO(idCancion);
		Cancion cancion = null;
		try {
			while (rs.next()) {
				cancion = new Cancion(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getFloat(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancion;
	}

	public Cancion obtenerCancionTitulo() {
		ResultSet rs = dcan.obtenerCancionTituloDAO(titulo);
		Cancion cancion = null;
		try {
			while (rs.next()) {
				cancion = new Cancion(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getFloat(5));

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

	public void eliminarCliente() {
		dcan.eliminarCancionDAO(idCancion);
	}

}
