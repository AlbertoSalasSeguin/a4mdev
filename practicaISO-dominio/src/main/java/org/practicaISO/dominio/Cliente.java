package org.practicaISO.dominio;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.practicaISO.persistencia.DAOCliente;

public class Cliente {
	private String nick;
	private String pass;
	private String email;
	private String nombre;
	private String apellidos;
	private String suscripcion;
	private String rol;
	private ArrayList<Cancion> listaCanciones;
	private DAOCliente dc;

	public Cliente(String nick, String pass, String email, String nombre, String apellidos, String suscripcion,
			String rol) {

		this.nick = nick;
		this.pass = pass;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.suscripcion = suscripcion;
		this.rol = rol;
		this.dc = new DAOCliente();
	}

	public Cliente() {
		this.dc = new DAOCliente();
	}

	public Cliente(String nick) {
		this.nick = nick;
		this.dc = new DAOCliente();
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

	public Cliente logearCliente() {
		ResultSet rs = dc.logearClienteDAO(nick, pass);
		Cliente cliente = null;
		try {
			while (rs.next()) {
				cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cliente;
	}

	public Cliente obtenerCliente() {
		ResultSet rs = dc.obtenerClienteDAO(nick);
		Cliente cliente = null;
		try {
			while (rs.next()) {
				cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<Cancion> obtenerCanciones() {
		ResultSet rs = dc.obtenerCancionesDAO(nick);
		ArrayList<Cancion> ac = new ArrayList<Cancion>();
		try {
			while (rs.next()) {
				ac.add(new Cancion(rs.getInt(1)));
			}
			this.setListaCanciones(ac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public void insertarCliente() {
		dc.insertarClienteDAO(nick, pass, email, nombre, apellidos, suscripcion, rol);
	}

	public void actualizarCliente() {
		dc.actualizarClienteDAO(pass, email, nombre, apellidos, suscripcion, rol, nick);
	}

	public void comprarCancion(int idCancion) {
		dc.comprarCancionDAO(nick, idCancion);
	}

	public void eliminarCliente() {
		dc.eliminarClienteDAO(nick);
	}

}
