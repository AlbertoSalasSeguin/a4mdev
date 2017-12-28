package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
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
	public void testInsertarAlbumNombreNuloDAO() {
		assertFalse(d.insertarAlbumDAO(30, null));
	}

	@Test
	public void testInsertarAlbumIdExistenteDAO() {
		assertFalse(d.insertarAlbumDAO(1, "Album ejemplo"));
	}
	
	@Test
	public void testActualizarAlbumDAOExistente() {
		assertTrue(d.actualizarAlbumDAO(1, "One"));
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
			assertEquals(1, rs.getInt("idcancion"));
			assertTrue(rs.next());
			assertEquals(3, rs.getInt("idcancion"));
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerIdsCancionesAlbumInexistenteDAO() {
		try (ResultSet rs = d.obtenerIdsCancionesAlbumDAO(46)) {
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminarAlbumDAO() {
		assertTrue(d.eliminarAlbumDAO(30));
	}

}