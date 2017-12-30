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
	private final DAOCliente dcliente;

	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;

	public Cliente(final String nickCons, final String passCons, final String emailCons, final String nombreCons, final String apellidosCons, final String suscripcionCons,
			final String rolCons) {

		this.nick = nickCons;
		this.pass = passCons;
		this.email = emailCons;
		this.nombre = nombreCons;
		this.apellidos = apellidosCons;
		this.suscripcion = suscripcionCons;
		this.rol = rolCons;
		this.dcliente = new DAOCliente();
	}

	public Cliente() {
		this.dcliente = new DAOCliente();
	}

	public Cliente(final String nickCons) {
		this.nick = nickCons;
		this.dcliente = new DAOCliente();
	}

	public String getNick() {
		return nick;
	}

	public void setNick(final String newNick) {
		this.nick = newNick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(final String newPass) {
		this.pass = newPass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String newEmail) {
		this.email = newEmail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String newNombre) {
		this.nombre = newNombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(final String newApellidos) {
		this.apellidos = newApellidos;
	}

	public String getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(final String newSuscripcion) {
		this.suscripcion = newSuscripcion;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(final ArrayList<Cancion> newListaCanciones) {
		this.listaCanciones = newListaCanciones;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(final String newRol) {
		this.rol = newRol;
	}

	public Cliente logearCliente() {
		final ResultSet resultSet = dcliente.logearClienteDAO(nick, pass);
		Cliente cliente = null;
		try {
			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(THREE), resultSet.getString(FOUR),
						resultSet.getString(FIVE), resultSet.getString(SIX), resultSet.getString(SEVEN));
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
				cliente = new Cliente(resultSet.getString(1), resultSet.getString(2), resultSet.getString(THREE), resultSet.getString(FOUR),
						resultSet.getString(FIVE), resultSet.getString(SIX), resultSet.getString(SEVEN));

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

	public void comprarCancion(final int idCancionCC) {
		dcliente.comprarCancionDAO(nick, idCancionCC);
	}

	public void eliminarCliente() {
		dcliente.eliminarClienteDAO(nick);
	}

}
