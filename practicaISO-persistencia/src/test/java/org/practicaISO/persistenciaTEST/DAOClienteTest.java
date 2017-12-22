package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.practicaISO.persistencia.DAOCliente;

public class DAOClienteTest {

	DAOCliente d;

	@Before
	public void iniciar() {
		d = new DAOCliente();
	}

	@Test
	public void testLogearClienteDAO() {
		try (ResultSet rs = d.logearClienteDAO("a4m", "1234")) {
			assertTrue(rs.next());
			assertEquals("a4m", rs.getString("nick"));
			assertEquals("1234", rs.getString("pass"));
			assertEquals("a4m", rs.getString("email"));
			assertEquals("A4Mdev", rs.getString("nombre"));
			assertEquals("DEVELOPERS", rs.getString("apellidos"));
			assertEquals("premium", rs.getString("suscripcion"));
			assertEquals("Admin", rs.getString("rol"));
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerClienteDAO() {
		try (ResultSet rs = d.obtenerClienteDAO("a4m")) {
			assertTrue(rs.next());
			assertEquals("a4m", rs.getString("nick"));
			assertEquals("1234", rs.getString("pass"));
			assertEquals("a4m", rs.getString("email"));
			assertEquals("A4Mdev", rs.getString("nombre"));
			assertEquals("DEVELOPERS", rs.getString("apellidos"));
			assertEquals("premium", rs.getString("suscripcion"));
			assertEquals("Admin", rs.getString("rol"));
			assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObtenerCancionesDAO() {
		try (ResultSet rs = d.obtenerCancionesDAO("Alberto")) {
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
	public void testInsertarClienteExistente() {
		assertFalse(d.insertarClienteDAO("a4m", "1234", "a4m", "A4Mdev", "DEVELOPERS", "premium", "Admin"));
	}

	@Test
	public void testInsertarClienteNickNulo() {
		String nicknulo = null;
		assertFalse(d.insertarClienteDAO(nicknulo, "345", "alberto@gmail", "alberto", "salas", "premium", "Admin"));
		assertNull(nicknulo);
	}

	@Test
	public void testInsertarClientePassNula() {
		String passnulo = null;
		assertFalse(
				d.insertarClienteDAO("Alberto", passnulo, "alberto@gmail", "alberto", "salas", "premium", "Usuario"));
		assertNull(passnulo);
	}

	@Test
	public void testInsertarClienteEmailNulo() {
		String emailnulo = null;
		assertFalse(d.insertarClienteDAO("Alberto", "123", emailnulo, "alberto", "salas", "premium", "Usuario"));
		assertNull(emailnulo);
	}

	@Test
	public void testInsertarClienteSinsuscripcionExistente() {
		String suscnulo = null;
		assertFalse(d.insertarClienteDAO("Alberto", "123", "salas@gmail", "alberto", "salas", suscnulo, "Usuario"));
		assertNull(suscnulo);
	}

	@Test
	public void testInsertarClienteSinRol() {
		String rolnulo = null;
		assertFalse(d.insertarClienteDAO("Alberto", "123", "salas@gmail", "alberto", "salas", "normal", rolnulo));
		assertNull(rolnulo);
	}

	@Test
	public void testActualizarClienteDAOExistente() {
		assertTrue(d.actualizarClienteDAO("1234", "a4m", "A4Mdev", "DEVELOPERS", "premium", "Admin", "a4m"));
	}

	@Test
	public void testActualizarClienteDAOPassNula() {
		assertFalse(d.actualizarClienteDAO(null, "a4m", "A4Mdev", "DEVELOPERS", "premium", "Admin", "a4m"));
	}

	@Test
	public void testActualizarClienteDAOEmailNulo() {
		assertFalse(d.actualizarClienteDAO("1234", null, "A4Mdev", "DEVELOPERS", "premium", "Admin", "a4m"));
	}

	@Test
	public void testActualizarClienteDAONombreNulo() {
		assertFalse(d.actualizarClienteDAO("1234", "a4m", null, "DEVELOPERS", "premium", "Admin", "a4m"));
	}

	@Test
	public void testActualizarClienteDAOApellidosNulos() {
		assertFalse(d.actualizarClienteDAO("1234", "a4m", "A4Mdev", null, "premium", "Admin", "a4m"));
	}

	@Test
	public void testActualizarClienteDAOSinSubscripcion() {
		assertFalse(d.actualizarClienteDAO("1234", "a4m", "A4Mdev", "DEVELOPERS", null, "Admin", "a4m"));
	}

	@Test
	public void testComprarCancionYaAdquirida() {
		assertFalse(d.comprarCancionDAO("a4m", 1));
	}
	
	@Test
	public void testComprarCancionNickNulo() {
		assertFalse(d.comprarCancionDAO(null, 1));
	}

	@Test
	public void testEliminarClienteDAO() {
		String nicknulo=null;
		d.eliminarClienteDAO(nicknulo);
		assertNull(nicknulo);
	}

}
