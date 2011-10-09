package com.nhpatt.actividades;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.nhpatt.modelos.Nota;

public class InsertarNotaActivity extends Activity implements OnClickListener {

	private static final String NOTA_ACTUAL = "NOTA_ACTUAL";
	public static final String ULTIMA_NOTA = "ULTIMA_NOTA";
	public static final String NOTA = "NOTA";

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevanota);

		final Button incluirNota = (Button) findViewById(R.id.nuevaNota);
		incluirNota.setOnClickListener(this);
	}

	public void onClick(final View v) {
		final EditText campoNota = (EditText) findViewById(R.id.campoNuevaNota);
		final Intent intent = new Intent();
		final Nota nota = new Nota(campoNota.getText().toString());
		intent.putExtra(NOTA, nota);
		setResult(Activity.RESULT_OK, intent);

		final SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		final SharedPreferences.Editor editor = preferences.edit();
		editor.putString(ULTIMA_NOTA, nota.getDescripcion());
		editor.commit();

		finish();
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		outState.putString(NOTA_ACTUAL,
				((EditText) findViewById(R.id.campoNuevaNota)).getText()
						.toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(final Bundle savedInstanceState) {
		final String textoNota = savedInstanceState.getString(NOTA_ACTUAL);
		((EditText) findViewById(R.id.campoNuevaNota)).setText(textoNota);
		super.onRestoreInstanceState(savedInstanceState);
	}
}
