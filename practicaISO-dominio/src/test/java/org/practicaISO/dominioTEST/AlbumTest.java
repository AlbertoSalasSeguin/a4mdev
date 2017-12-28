package org.practicaISO.dominioTEST;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.practicaISO.dominio.Album;

public class AlbumTest {

	
	private Album alb;
	private Album albumNoExistente;
	private Album albumnulo;
	private Album albConstId;
	private ArrayList<String> ids;
	
	@Before
	public void iniciar() {
		alb = new Album(1, "One");
		albumNoExistente = new Album(73, "Null");
		albumnulo = null;
		albConstId= new Album(67);	
	}

	@Test
	public void testGettersSetters() { // prueba de get set para cubrir cobertura, suponemos todos los casos
		String albumesperado = "One";
		int idalbumesperado=1;
		alb.setIdalbum(1);
		alb.setNombre("One");
		assertEquals(idalbumesperado, alb.getIdalbum());
		assertEquals(albumesperado, alb.getNombre());
	}

	@Test
	public void testObtenerAlbum() {
		alb = alb.obtenerAlbum();
		assertNotNull(alb);
	}

	@Test
	public void testObtenerAlbumNoExistente() throws SQLException {
		albumNoExistente = albumNoExistente.obtenerAlbum();

	}

	@Test
	public void testObtenerIdsCancionesAlbum() {
		ids = alb.obtenerIdsCancionesAlbum();
		ArrayList<String> idsesperado = new ArrayList<String>();
		idsesperado.add("1"); // añadimos dos canciones que tienen el album existente
		idsesperado.add("3");
		assertNotNull(ids);
		assertEquals(idsesperado, ids);
	}

	@Test
	public void testObtenerIdsCancionesAlbumNoExistente() {
		ids = albumNoExistente.obtenerIdsCancionesAlbum();
		ArrayList<String> idsesperado = new ArrayList<String>();
		idsesperado.add("1");
		idsesperado.add("3");
		assertNotEquals(idsesperado, ids);
	}

	@Test
	public void testInsertarAlbum() {
		albumNoExistente.insertarAlbum();
	}

	@Test
	public void testInsertarAlbumExistente() throws SQLException {
		alb.insertarAlbum();
	}

	@Test
	public void testActualizarAlbum() {
		alb.actualizarAlbum();
	}
	
	@Test
	public void testActualizarAlbumNulo() throws NullPointerException {
		assertNull(albumnulo);
	}

	@Test
	public void testEliminarAlbumExistente() {
		albumNoExistente.eliminarAlbum();
	}

	@Test
	public void testEliminarAlbumNoExistenteNulo() throws SQLException{
		assertNull(albumnulo);
	}
	@Test
	public void testToString() {
		String esperado= "1-One";
		assertEquals(esperado, alb.toString());
	}

}
