package com.nhpatt.notas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NotasActivity extends Activity {
	private static final String NOTAS_TAG = "Notas";

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.e(NOTAS_TAG, "Creando la actividad");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e(NOTAS_TAG, "Empezando la actividad");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e(NOTAS_TAG, "Resumiendo la actividad");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e(NOTAS_TAG, "Pausando la actividad");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e(NOTAS_TAG, "Parando la actividad");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(NOTAS_TAG, "Destruyendo la actividad");
	}

}