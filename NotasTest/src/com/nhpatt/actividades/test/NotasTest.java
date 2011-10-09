package com.nhpatt.actividades.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.nhpatt.actividades.NotasActivity;

public class NotasTest extends ActivityInstrumentationTestCase2<NotasActivity> {

	public NotasTest() {
		super("com.nhpatt.actividades", NotasActivity.class);
	}

	private NotasActivity actividad;
	private TextView titulo;
	private String textoTitulo;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		actividad = getActivity();
		titulo = (TextView) actividad
				.findViewById(com.nhpatt.actividades.R.id.bienvenida);
		textoTitulo = actividad
				.getString(com.nhpatt.actividades.R.string.bienvenida);
	}

	public void testPreconditions() {
		assertNotNull(titulo);
	}

	public void testText() {
		assertEquals(textoTitulo, (String) titulo.getText());
	}

}
