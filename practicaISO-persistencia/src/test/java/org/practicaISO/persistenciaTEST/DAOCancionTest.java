package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import org.junit.Test;
import org.practicaISO.persistencia.DAOCancion;

public class DAOCancionTest {

	private DAOCancion dc= new DAOCancion();
	
	@Test
	public void testLeerCancionesDAO() {
	}

	@Test
	public void testObtenerCancionesAutorDAO() {
	}

	@Test
	public void testObtenerIdsCancionesDAO() {
	}

	@Test
	public void testObtenerCancionIdDAO() {
	}

	@Test
	public void testObtenerCancionTituloDAO() {
	}

	@Test
	public void testInsertarCancion() {
		
	}

	@Test
	public void testActualizarCancionDAOExistente() {
		assertTrue(dc.actualizarCancionDAO("Time of dying", 1,"Three days grace", 1, 10));
	}
	@Test
	public void testActualizarCancionDAONombreNulo() {
		assertTrue(dc.actualizarCancionDAO(null, 1,"Three days grace", 1, 10));
	}
	@Test
	public void testActualizarCancionDAOAutorNulo() {
		assertTrue(dc.actualizarCancionDAO("Time of dying", 1,null, 1, 10));
	}
	@Test
	public void testActualizarCancionDAOIdInexistente() {
		assertTrue(dc.actualizarCancionDAO("Time of dying", 333331,"Three days grace", 1, 10));
	}
	@Test
	public void testActualizarCancionDAOIdNulo() {
		assertTrue(dc.actualizarCancionDAO("Time of dying", -3,"Three days grace", 1, 10));
	}
	@Test
	public void testEliminarCancionDAOIdNulo() {
		assertTrue(dc.eliminarCancionDAO(-23));
	}
	
	

}
