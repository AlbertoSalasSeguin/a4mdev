package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.practicaISO.persistencia.DAOCancion;

public class DAOCancionTest {

	private DAOCancion dc ;

	@Before
	public void inicializar() {
		dc = new DAOCancion();
		dc.eliminarCancionDAO(77); // para que cada vez que se ejecuten los test, no devuelva error por duplicidad
		dc.actualizarCancionDAO("Time of dying", 1, "Three days grace", 1, 10);
	}

	@After
	public void postTests() {
		dc.insertarCancion("Todo cambio", 10, "Becky G", 8, 23);
		dc.actualizarCancionDAO("Time of dying", 1, "Three days grace", 1, 10);
	}

	@Test
	public void testleerCancionesDAO () {
		try (ResultSet rs = dc.leerCancionesDAO()) {
			assertTrue(rs.next());
			assertEquals("Time of dying", rs.getString("titulo"));
			assertEquals(1, rs.getInt("idcancion"));
			assertEquals("Three days grace", rs.getString("autor"));
			assertEquals(1, rs.getInt("album"));
			assertEquals(10, rs.getFloat("precio"), 0.0f);
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testObtenerCancionesAutorDAO() {
		try (ResultSet rs = dc.obtenerCancionesAutorDAO("Egypt Central")) {
			assertTrue(rs.next());
			assertEquals("Taking you down", rs.getString("titulo"));
			assertEquals(2, rs.getInt("idcancion"));
			assertEquals("Egypt Central", rs.getString("autor"));
			assertEquals(2, rs.getInt("album"));
			assertEquals(20, rs.getFloat("precio"), 0.0f);

			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerIdsCancionesDAO() {
		try (ResultSet rs = dc.obtenerIdsCancionesDAO("Alberto")) {
			assertTrue(rs.next());
			assertEquals(5, rs.getInt("canciones"));
			assertTrue(rs.next());
			assertEquals(6, rs.getInt("canciones"));
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerCancionIdDAO() {
		try (ResultSet rs = dc.obtenerCancionIdDAO(4)) {
			assertTrue(rs.next());
			assertEquals("Havana", rs.getString("titulo"));
			assertEquals(4, rs.getInt("idcancion"));
			assertEquals("Camila Cabello", rs.getString("autor"));
			assertEquals(3, rs.getInt("album"));
			assertEquals(12, rs.getFloat("precio"), 0.0f);

			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testObtenerCancionTituloDAO() {
		try (ResultSet rs = dc.obtenerCancionTituloDAO("This fire burns")) {
			assertTrue(rs.next());
			assertEquals("This fire burns", rs.getString("titulo"));
			assertEquals(7, rs.getInt("idcancion"));
			assertEquals("Killswitch Engage", rs.getString("autor"));
			assertEquals(5, rs.getInt("album"));
			assertEquals(16, rs.getFloat("precio"), 0.0f);

			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertarCancion() {
		assertTrue(dc.insertarCancion("Ejemplo titulo cancion", 77, "Ejemplo autor", 1, 23));
	}

	@Test
	public void testInsertarCancionExistente() {
		assertFalse(dc.insertarCancion("Todo cambio", 10, "Becky G", 8, 23));
	}
	
	@Test
	public void testInsertarCancionNombreNulo() {
		String titulonulo = null;
		assertFalse(dc.insertarCancion(titulonulo, 77, "Ejemplo autor", 1, 23));
		assertNull(titulonulo);
	}

	@Test
	public void testInsertarCancionAutorNulo() {
		String autornulo = null;
		assertFalse(dc.insertarCancion("Ejemplo titulo cancion", 77, autornulo, 1, 23));
		assertNull(autornulo);
	}

	@Test
	public void testInsertarCancionAlbumNoExistente() {
		assertFalse(dc.insertarCancion("Ejemplo titulo cancion", 77, "Ejemplo autor", 455, 23));
	}

	@Test
	public void testActualizarCancionDAOExistente() {
		assertTrue(dc.actualizarCancionDAO("Time of dying", 1, "Three days grace", 1, 15));
	}

	@Test
	public void testActualizarCancionDAOTituloNulo() {
		String titulonulo = null;
		dc.actualizarCancionDAO(titulonulo, 1, "Three days grace", 1, 10);
		assertNull(titulonulo);
	}

	@Test
	public void testActualizarCancionDAOAutorNulo() {
		String autornulo = null;
		dc.actualizarCancionDAO("Time of dying", 1, autornulo, 1, 10);
		assertNull(autornulo);
	}

	@Test
	public void testActualizarCancionDAOAlbumNoExistente() {
		assertFalse(dc.actualizarCancionDAO("Time of dying", 1, "Three days grace", 45556, 10));

	}

	
	@Test
	public void testEliminarCancionDAO() {
		assertTrue(dc.eliminarCancionDAO(10));
	}

}
