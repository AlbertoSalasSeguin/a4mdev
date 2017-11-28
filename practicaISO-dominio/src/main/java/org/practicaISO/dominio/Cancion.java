package org.practicaISO.dominio;

public class Cancion {

	private String titulo;
	private int idCancion;
	private String autor;
	private int album;
	private double precio;
	
	
	
	public Cancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public Cancion(String titulo, int idCancion, String autor, int album, double precio) {

		this.titulo = titulo;
		this.idCancion = idCancion;
		this.autor = autor;
		this.album = album;
		this.precio = precio;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		
		return this.getIdCancion()+ "-" +this.getTitulo()+ " - " +this.getAutor();
	}
	
	
	
}
