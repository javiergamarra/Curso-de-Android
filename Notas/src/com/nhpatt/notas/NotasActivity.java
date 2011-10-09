package com.nhpatt.notas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NotasActivity extends Activity implements OnClickListener {

	private static final String NOTAS_TAG = "NOTAS";
	private final List<String> notas = new ArrayList<String>();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button button = (Button) findViewById(R.id.incluirNuevaNota);
		button.setOnClickListener(this);
	}

	public void onClick(final View v) {
		final EditText campoNuevaNota = (EditText) findViewById(R.id.nuevaNota);
		final String textoNota = campoNuevaNota.getText().toString();
		notas.add(textoNota);
		Log.e(NOTAS_TAG, textoNota);
	}
}