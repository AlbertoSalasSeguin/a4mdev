package org.practicaISO.persistenciaTEST;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import org.practicaISO.persistencia.DAOCliente;

public class DAOClienteTest {

	DAOCliente d;

	@Before
	public void iniciar() {
		d = new DAOCliente();
	}

	@Test(expected= NullPointerException.class)
	public void test() {
		
		String valor1="pepito";
		String valor2="asdf";
		String valorEsperado;
		String valorResultSet;
		ResultSet rs =d.logearClienteDAO(valor1, valor2);
		
//		assertEquals(valorEsperado,valorResultSet);
		
		
	}

}
