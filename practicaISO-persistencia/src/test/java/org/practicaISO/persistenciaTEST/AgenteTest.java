package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;
import org.practicaISO.persistencia.Agente;

public class AgenteTest {
	Agente a = new Agente();
	Connection connection;

	@Test
	public void testIfConnectionNotNull() {
		connection = a.getConexion();
		assertNotNull(connection);
	}

}
