package org.practicaISO.dominio;

public class Album {

	private int idalbum;
	private String nombre;
	
	public Album(int idalbum, String nombre) {
		this.idalbum = idalbum;
		this.nombre = nombre;
	}

	public Album(int idalbum) {
		this.idalbum=idalbum;
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
	
	public String toString() {
		return this.getIdalbum()+"-"+this.getNombre();
	}
	
}
