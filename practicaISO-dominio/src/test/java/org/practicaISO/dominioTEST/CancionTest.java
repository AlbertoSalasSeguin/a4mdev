package org.practicaISO.dominioTEST;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.practicaISO.dominio.Cancion;

public class CancionTest {

	private Cancion cancConsVacio;
	private Cancion cancConsId;
	private Cancion cancNoExistente;
	private Cancion cancConsTitulo;
	private Cancion canc;
	private ArrayList<Cancion> ac;
	private ArrayList<String> as;
	
	@Before
	public void iniciar() {
		cancConsVacio= new Cancion();
		cancConsId= new Cancion(2);
		cancConsTitulo= new Cancion("Ahora me llama");
		canc= new Cancion("Time of dying", 1, "Three days grace", 1, 10);
		cancNoExistente = new Cancion("prueba", 56, "p", 3, 45);
	}
	
	@Test 
	public void testGettersSetters() {
		String tituloesperado= "Time of dying";
		int idcancionesperado=1;
		String autoresperado="Three days grace";
		String tostringesperado1="1-Time of dying - Three days grace";
		String tostringesperado2="1-Time of dying - Three days grace - 10.0€";
		int idalbumesperado=1;
		float precioesperado=10;
		canc.setTitulo("Time of dying");
		canc.setIdCancion(1);
		canc.setAlbum(1);
		canc.setAutor("Three days grace");
		canc.setPrecio(10);
		assertEquals(idalbumesperado, canc.getAlbum());
		assertEquals(idcancionesperado, canc.getIdCancion());
		assertEquals(precioesperado, canc.getPrecio(),0.0f);
		assertEquals(tituloesperado, canc.getTitulo());
		assertEquals(autoresperado,canc.getAutor());
		assertEquals(tostringesperado1, canc.toString());
		assertEquals(tostringesperado2, canc.toStringCompra());
	}
	

	@Test
	public void testLeerCanciones() {
		ac=canc.leerCanciones();
		assertNotNull(ac);
	}

	@Test
	public void testObtenerCancionesAutor() {
		ac=canc.obtenerCancionesAutor();
		assertNotNull(ac);
		
	}

	@Test
	public void testObtenerCancionesUsuario() {
		as=canc.obtenerCancionesUsuario("Alberto");
		assertNotNull(as);
		String valoresperado1="5";
		String valoresperado2="6";
		assertEquals(valoresperado1, as.get(0));
		assertEquals(valoresperado2, as.get(1));
	}

	@Test
	public void testObtenerCancionId() {
		canc=canc.obtenerCancionId();
		assertNotNull(canc);
	}

	@Test
	public void testObtenerCancionTitulo() {
		canc=canc.obtenerCancionTitulo();
		assertNotNull(canc);
	}

	@Test
	public void testInsertarCancion() {
		cancNoExistente.insertarCancion();
	}
	@Test
	public void testInsertarCancionExistente() throws SQLException{
		canc.insertarCancion();
	}

	@Test
	public void testActualizarCancion() {
		canc.actualizarCancion();
	}

	@Test
	public void testEliminarCancion() {
		cancNoExistente.eliminarCancion();
	}

}
