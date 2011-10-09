package com.nhpatt.notas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InsertarNotaActivity extends Activity implements OnClickListener {

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
		intent.putExtra(NOTA, campoNota.getText().toString());
		setResult(Activity.RESULT_OK, intent);
		finish();
	}
}
