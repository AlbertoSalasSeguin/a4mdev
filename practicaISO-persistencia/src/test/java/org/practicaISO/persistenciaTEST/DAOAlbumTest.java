package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.practicaISO.persistencia.DAOAlbum;

public class DAOAlbumTest {
	
	DAOAlbum d;

	@Before
	public void iniciar() {
		d = new DAOAlbum();
	}
	
	@Test
	public void testObtenerAlbumDAO() {
		try (ResultSet rs = d.obtenerAlbumDAO(1)) {
			assertTrue(rs.next());
			assertEquals(1, rs.getInt("idalbum"));
			assertEquals("One", rs.getString("nombre"));

			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertarAlbumDAO() {
		assertTrue(d.insertarAlbumDAO(30, "Album Ejemplo"));
	}
	
	@Test
	public void testActualizarAlbumDAOExistente() {
		assertFalse(d.actualizarAlbumDAO(1, "One"));
	}
	
	@Test
	public void testActualizarAlbumDAONombreNulo() {
		String nombrenulo = null;
		d.actualizarAlbumDAO(1, nombrenulo);
		assertNull(nombrenulo);
	}
	
	@Test
	public void testObtenerIdsCancionesAlbumDAO() {
		try (ResultSet rs = d.obtenerIdsCancionesAlbumDAO(1)) {
			assertTrue(rs.next());
			assertEquals(4, rs.getInt("album"));
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminarAlbumDAO() {
		assertTrue(d.eliminarAlbumDAO(4));
	}

}