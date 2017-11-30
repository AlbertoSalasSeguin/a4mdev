package org.practicaISO.dominio;

import java.util.ArrayList;

public class Cliente {
	private String nick;
	private String pass;
	private String email;
	private String nombre;
	private String apellidos;
	private String suscripcion;
	private String rol;
	private ArrayList<Cancion> listaCanciones;
	
	public Cliente(String nick, String pass, String email, String nombre, String apellidos, String suscripcion, String rol) {
		
		this.nick = nick;
		this.pass = pass;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.suscripcion = suscripcion;
		this.rol=rol;
	}

	public Cliente() {
	
	}

	public Cliente(String nick) {
		this.nick=nick;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(String suscripcion) {
		this.suscripcion = suscripcion;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	

}
