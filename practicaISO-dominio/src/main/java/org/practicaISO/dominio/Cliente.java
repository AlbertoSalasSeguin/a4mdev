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
	final private DAOCliente dcliente;

	public Cliente(final String nick, final String pass, final String email, final String nombre, final String apellidos, final String suscripcion,
			final String rol) {

		this.nick = nick;
		this.pass = pass;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.suscripcion = suscripcion;
		this.rol = rol;
		this.dcliente = new DAOCliente();
	}

	public Cliente() {
		this.dcliente = new DAOCliente();
	}

	public Cliente(final String nick) {
		this.nick = nick;
		this.dcliente = new DAOCliente();
	}

	public String getNick() {
		return nick;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(final String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(final String suscripcion) {
		this.suscripcion = suscripcion;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(final ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(final String rol) {
		this.rol = rol;
	}

	public Cliente logearCliente() {
		final ResultSet resultSet = dcliente.logearClienteDAO(nick, pass);
		Cliente cliente = null;
		try {
			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cliente;
	}

	public Cliente obtenerCliente() {
		final ResultSet resultSet = dcliente.obtenerClienteDAO(nick);
		Cliente cliente = null;
		try {
			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<Cancion> obtenerCanciones() {
		final ResultSet resultSet = dcliente.obtenerCancionesDAO(nick);
		final ArrayList<Cancion> arrayCanciones = new ArrayList<Cancion>();
		try {
			while (resultSet.next()) {
				arrayCanciones.add(new Cancion(resultSet.getInt(1)));
			}
			this.setListaCanciones(arrayCanciones);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayCanciones;
	}

	public void insertarCliente() {
		dcliente.insertarClienteDAO(nick, pass, email, nombre, apellidos, suscripcion, rol);
	}

	public void actualizarCliente() {
		dcliente.actualizarClienteDAO(pass, email, nombre, apellidos, suscripcion, rol, nick);
	}

	public void comprarCancion(final int idCancion) {
		dcliente.comprarCancionDAO(nick, idCancion);
	}

	public void eliminarCliente() {
		dcliente.eliminarClienteDAO(nick);
	}

}
