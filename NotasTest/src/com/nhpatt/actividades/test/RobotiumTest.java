package com.nhpatt.actividades.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;
import com.nhpatt.actividades.NotasActivity;

public class RobotiumTest extends
		ActivityInstrumentationTestCase2<NotasActivity> {

	private Solo solo;

	public RobotiumTest() {
		super("com.nhpatt.actividades", NotasActivity.class);

	}

	@Override
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Smoke
	public void testAddNote() throws Exception {
		solo.clickOnMenuItem("Preferencias");
		solo.assertCurrentActivity("Expected PreferenciasActivity",
				"PreferenciasActivity");
		solo.goBack();
		solo.clickOnMenuItem("Añadir Nueva Nota");
		solo.enterText(0, "Nota 1");
		solo.clickOnButton("Añadir Nueva Nota");
		final boolean expected = true;
		final boolean actual = solo.searchText("Nota 1");
		assertEquals(expected, actual);

	}

	@Override
	public void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (final Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}
}
