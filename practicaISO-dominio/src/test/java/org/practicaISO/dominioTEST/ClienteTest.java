package org.practicaISO.dominioTEST;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.practicaISO.dominio.Cancion;
import org.practicaISO.dominio.Cliente;

public class ClienteTest {

	private Cliente client;
	private Cliente clientConsVacio;
	private Cliente clientConsNick;
	private Cliente clientNulo;
	private Cliente clientPrueba;
	private ArrayList<Cancion> ac;
	
	@Before
	public void iniciar() {
		client = new Cliente("a4m", "1234", "a4m", "A4Mdev", "DEVELOPERS", "premium", "Admin");
		clientPrueba = new Cliente("prueba", "1234", "p", "p", "p", "premium", "Admin");
		clientConsVacio = new Cliente();
		clientNulo=null;
		clientConsNick = new Cliente("a4m");
	}

	@Test
	public void testGettersSetters() {
		client.setNick("a4m");
		client.setPass("1234");
		client.setEmail("a4m");
		client.setNombre("A4Mdev");
		client.setListaCanciones(null);
		client.setApellidos("DEVELOPERS");
		client.setSuscripcion("premium");
		client.setRol("Admin");
		String nickesperado = "a4m";
		String passesperado = "1234";
		String emailesperado = "a4m";
		String nombreesperado = "A4Mdev";
		String apellidosesperado = "DEVELOPERS";
		String suscripcionesperado = "premium";
		String rolesperado = "Admin";
		assertEquals(nickesperado, client.getNick());
		assertEquals(passesperado, client.getPass());
		assertEquals(emailesperado, client.getEmail());
		assertEquals(nombreesperado, client.getNombre());
		assertNull(client.getListaCanciones());
		assertEquals(apellidosesperado, client.getApellidos());
		assertEquals(suscripcionesperado, client.getSuscripcion());
		assertEquals(rolesperado, client.getRol());

	}

	@Test
	public void testLogearCliente() {
		Cliente resultado = client.logearCliente();
		assertEquals(resultado.getNick(), client.getNick());
		assertEquals(resultado.getPass(), client.getPass());
		assertEquals(resultado.getEmail(), client.getEmail());
		assertEquals(resultado.getNombre(), client.getNombre());
		assertEquals(resultado.getApellidos(), client.getApellidos());
		assertEquals(resultado.getSuscripcion(), client.getSuscripcion());
		assertEquals(resultado.getRol(), client.getRol());
	}


	@Test
	public void testObtenerCliente() {
		Cliente resultado = client.obtenerCliente();
		assertEquals(resultado.getNick(), client.getNick());
		assertEquals(resultado.getPass(), client.getPass());
		assertEquals(resultado.getEmail(), client.getEmail());
		assertEquals(resultado.getNombre(), client.getNombre());
		assertEquals(resultado.getApellidos(), client.getApellidos());
		assertEquals(resultado.getSuscripcion(), client.getSuscripcion());
		assertEquals(resultado.getRol(), client.getRol());
	}

	@Test
	public void testObtenerCanciones() {
		ac=client.obtenerCanciones();
		assertNotNull(ac);
		int idcancionesperada = 1;
		assertEquals(idcancionesperada, ac.get(0).getIdCancion());
	}

	@Test
	public void testInsertarClienteExistente() throws SQLException {
		client.insertarCliente();
	}

	@Test 
	public void testInsertarCliente() {
		clientPrueba.insertarCliente();
	}
	
	@Test
	public void testActualizarCliente() {
		clientPrueba.actualizarCliente();
	}

	@Test
	public void testComprarCancion() {
		int idcancion = 1;
		clientPrueba.comprarCancion(idcancion);
	}
	
	@Test
	public void testComprarCancionNoExistente() throws SQLException {
		int idcancionNoExistente=56;
		clientPrueba.comprarCancion(idcancionNoExistente);
	}

	@Test
	public void testEliminarCliente() {
		clientPrueba.eliminarCliente();
	}

	@Test
	public void testEliminarClienteNulo() throws SQLException {
		assertNull(clientNulo);
	}
	
}
