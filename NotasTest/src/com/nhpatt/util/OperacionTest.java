package com.nhpatt.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OperacionTest {

	private Operacion operacion;

	@Before
	public void setUp() {
		operacion = new Operacion();
	}

	@Test
	public void testOperacionDevuelveSiEsMayorQueCero() {
		assertTrue("Este test devuelve si es mayor que cero",
				operacion.esMayorQueCero(6));
	}

	@Test
	public void testOperacionNumeroNegativoDevuelveMenorQueCero() {
		assertFalse("Este test devuelve si es menor que cero",
				operacion.esMayorQueCero(-6));
	}

}
