package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.practicaISO.persistencia.DAOCancion;

public class DAOCancionTest {

	private DAOCancion dc;

	@Before
	public void inicializar() {
		int idCancion = 77;
		String titulo = "Time of dying";
		int idCancion1 = 1;
		String autor = "Three days grace";
		int idAlbum = 1;
		float precio = 10;
		
		dc = new DAOCancion();
		dc.eliminarCancionDAO(idCancion); // para que cada vez que se ejecuten los test, no devuelva error por
											// duplicidad
		dc.actualizarCancionDAO(titulo, idCancion1, autor, idAlbum, precio);
	}

	@After
	public void postTests() {
		String titulo = "Todo cambio";
		int idCancion = 10;
		String autor = "Becky G";
		int idAlbum = 8;
		float precio = 23;

		String titulo2 = "Time of dying";
		int idCancion2 = 1;
		String autor2 = "Three days grace";
		int idAlbum2 = 1;
		float precio2 = 10;

		dc.insertarCancion(titulo, idCancion, autor, idAlbum, precio);
		dc.actualizarCancionDAO(titulo2, idCancion2, autor2, idAlbum2, precio2);
	}

	@Test
	public void testleerCancionesDAO() throws SQLException {
		String valorEsperadoTitulo = "Time of dying";
		int valorEsperadoIdCancion = 1;
		String valorEsperadoAutor = "Three days grace";
		int valorEsperadoAlbum = 1;
		float valorEsperadoPrecio = 10;

		ResultSet rs = dc.leerCancionesDAO();
		assertTrue(rs.next());
		assertEquals(valorEsperadoTitulo, rs.getString("titulo"));
		assertEquals(valorEsperadoIdCancion, rs.getInt("idcancion"));
		assertEquals(valorEsperadoAutor, rs.getString("autor"));
		assertEquals(valorEsperadoAlbum, rs.getInt("album"));
		assertEquals(valorEsperadoPrecio, rs.getFloat("precio"), 0.0f);
		assertTrue(rs.next());
	}

	@Test
	public void testObtenerCancionesAutorDAO() throws SQLException {
		String autor = "Egypt Central";
		String valorEsperadoTitulo = "Taking you down";
		int valorEsperadoIdcancion = 2;
		String valorEsperadoAutor = "Egypt Central";
		int valorEsperadoAlbum = 2;
		float valorEsperadoPrecio = 20;

		ResultSet rs = dc.obtenerCancionesAutorDAO(autor);
		assertTrue(rs.next());
		assertEquals(valorEsperadoTitulo, rs.getString("titulo"));
		assertEquals(valorEsperadoIdcancion, rs.getInt("idcancion"));
		assertEquals(valorEsperadoAutor, rs.getString("autor"));
		assertEquals(valorEsperadoAlbum, rs.getInt("album"));
		assertEquals(valorEsperadoPrecio, rs.getFloat("precio"), 0.0f);

		assertFalse(rs.next());
	}

	@Test
	public void testObtenerIdsCancionesDAO() throws SQLException {
		String nick = "Alberto";
		int valorEsperadoCancion1 = 5;
		int valorEsperadoCancion2 = 6;
		
		ResultSet rs = dc.obtenerIdsCancionesDAO(nick);
		assertTrue(rs.next());
		assertEquals(valorEsperadoCancion1, rs.getInt("canciones"));
		assertTrue(rs.next());
		assertEquals(valorEsperadoCancion2, rs.getInt("canciones"));
		assertFalse(rs.next());
	}

	@Test
	public void testObtenerCancionIdDAO() throws SQLException {
		int idCancion = 4;
		String valorEsperadoTitulo = "Havana";
		int valorEsperadoIdcancion = 4;
		String valorEsperadoAutor = "Camila Cabello";
		int valorEsperadoAlbum = 3;
		float valorEsperadoPrecio = 12;
		
		ResultSet rs = dc.obtenerCancionIdDAO(idCancion);
		assertTrue(rs.next());
		assertEquals(valorEsperadoTitulo, rs.getString("titulo"));
		assertEquals(valorEsperadoIdcancion, rs.getInt("idcancion"));
		assertEquals(valorEsperadoAutor, rs.getString("autor"));
		assertEquals(valorEsperadoAlbum, rs.getInt("album"));
		assertEquals(valorEsperadoPrecio, rs.getFloat("precio"), 0.0f);

		assertFalse(rs.next());

	}

	@Test
	public void testObtenerCancionTituloDAO() throws SQLException {
		String titulo = "This fire burns";
		String valorEsperadoTitulo = "This fire burns";
		int valorEsperadoIdCancion = 7;
		String valorEsperadoAutor = "Killswitch Engage";
		int valorEsperadoAlbum = 5;
		float valorEsperadoPrecio = 16;
		
		ResultSet rs = dc.obtenerCancionTituloDAO(titulo);
		assertTrue(rs.next());
		assertEquals(valorEsperadoTitulo, rs.getString("titulo"));
		assertEquals(valorEsperadoIdCancion, rs.getInt("idcancion"));
		assertEquals(valorEsperadoAutor, rs.getString("autor"));
		assertEquals(valorEsperadoAlbum, rs.getInt("album"));
		assertEquals(valorEsperadoPrecio, rs.getFloat("precio"), 0.0f);

		assertFalse(rs.next());

	}

	@Test
	public void testInsertarCancion() {
		String valorEsperadoTitulo = "Ejemplo titulo cancion";
		int valorEsperadoIdcancion = 77;
		String valorEsperadoAutor = "Ejemplo autor";
		int valorEsperadoAlbum = 1;
		float valorEsperadoPrecio = 23;
		
		assertTrue(dc.insertarCancion(valorEsperadoTitulo, valorEsperadoIdcancion, valorEsperadoAutor, valorEsperadoAlbum, valorEsperadoPrecio));
	}

	@Test
	public void testInsertarCancionExistente() {
		String valorEsperadoTitulo = "Todo cambio";
		int valorEsperadoIdcancion = 10;
		String valorEsperadoAutor = "Becky G";
		int valorEsperadoAlbum = 8;
		float valorEsperadoPrecio = 23;
		
		assertFalse(dc.insertarCancion(valorEsperadoTitulo, valorEsperadoIdcancion, valorEsperadoAutor, valorEsperadoAlbum, valorEsperadoPrecio));
	}

	@Test
	public void testInsertarCancionNombreNulo() {
		String valorEsperadoTitulo = null;
		int valorEsperadoIdcancion = 77;
		String valorEsperadoAutor = "Ejemplo autor";
		int valorEsperadoAlbum = 1;
		float valorEsperadoPrecio = 23;
		
		assertFalse(dc.insertarCancion(valorEsperadoTitulo, valorEsperadoIdcancion, valorEsperadoAutor, valorEsperadoAlbum, valorEsperadoPrecio));
		assertNull(valorEsperadoTitulo);
	}

	@Test
	public void testInsertarCancionAutorNulo() {
		String valorEsperadoTitulo = "Ejemplo titulo cancion";
		int valorEsperadoIdCancion = 77;
		String valorEsperadoAutor = null;
		int valorEsperadoIdAlbum = 1;
		float valorEsperadoPrecio = 23;
		
		assertFalse(dc.insertarCancion(valorEsperadoTitulo, valorEsperadoIdCancion, valorEsperadoAutor, valorEsperadoIdAlbum, valorEsperadoPrecio));
		assertNull(valorEsperadoAutor);
	}

	@Test
	public void testInsertarCancionAlbumNoExistente() {
		String valorEsperadoTitulo = "Ejemplo titulo cancion";
		int valorEsperadoIdCancion = 77;
		String valorEsperadoAutor = "Ejemplo autor";
		int valorEsperadoIdAlbum = 455;
		float valorEsperadoPrecio = 23;
		
		assertFalse(dc.insertarCancion(valorEsperadoTitulo, valorEsperadoIdCancion, valorEsperadoAutor, valorEsperadoIdAlbum, valorEsperadoPrecio));
	}

	@Test
	public void testActualizarCancionDAOExistente() {
		String valorEsperadoTitulo = "Time of dying";
		int valorEsperadoIdCancion = 1;
		String valorEsperadoAutor = "Three days grace";
		int valorEsperadoIdAlbum = 1;
		float valorEsperadoPrecio = 15;
		
		assertTrue(dc.actualizarCancionDAO(valorEsperadoTitulo, valorEsperadoIdCancion, valorEsperadoAutor, valorEsperadoIdAlbum, valorEsperadoPrecio));
	}

	@Test
	public void testActualizarCancionDAOTituloNulo() {
		String valorEsperadoTitulo = null;
		int valorEsperadoIdCancion = 1;
		String valorEsperadoAutor = "Three days grace";
		int valorEsperadoIdAlbum = 1;
		float valorEsperadoPrecio = 10;
		
		dc.actualizarCancionDAO(valorEsperadoTitulo, valorEsperadoIdCancion, valorEsperadoAutor, valorEsperadoIdAlbum, valorEsperadoPrecio);
		assertNull(valorEsperadoTitulo);
	}

	@Test
	public void testActualizarCancionDAOAutorNulo() {
		String valorEsperadoTitulo = "Time of dying";
		int valorEsperadoIdCancion = 1;
		String valorEsperadoAutor = null;
		int valorEsperadoIdAlbum = 1;
		float valorEsperadoPrecio = 10;
		
		dc.actualizarCancionDAO(valorEsperadoTitulo, valorEsperadoIdCancion, valorEsperadoAutor, valorEsperadoIdAlbum, valorEsperadoPrecio);
		assertNull(valorEsperadoAutor);
	}

	@Test
	public void testActualizarCancionDAOAlbumNoExistente() {
		String valorEsperadoTitulo = "Time of dying";
		int valorEsperadoIdCancion = 1;
		String valorEsperadoAutor = "Three days grace";
		int valorEsperadoIdAlbum = 45556;
		float valorEsperadoPrecio = 10;
		
		assertFalse(dc.actualizarCancionDAO(valorEsperadoTitulo, valorEsperadoIdCancion, valorEsperadoAutor, valorEsperadoIdAlbum, valorEsperadoPrecio));

	}

	@Test
	public void testEliminarCancionDAO() {
		int idCancion = 10;
		assertTrue(dc.eliminarCancionDAO(idCancion));
	}

}
