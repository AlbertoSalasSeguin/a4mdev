package org.practicaISO.dominioTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Test;
import org.practicaISO.persistencia.DAOAlbum;

public class AlbumTest {
	DAOAlbum da = new DAOAlbum();

	@Test
	public void testObtenerAlbumInexistente() {
		ResultSet resultado=da.obtenerAlbumDAO(-5);
		ResultSet esperado= null;
		assertEquals(esperado, resultado);
	}

//	@Test
//	public void testObtenerIdsCancionesAlbum() {
//
//	}
//
//	@Test
//	public void testInsertarAlbum() {
//
//	}
//
//	@Test
//	public void testActualizarAlbum() {
//
//	}
//
//	@Test
//	public void testEliminarAlbum() {
//
//	}

}
