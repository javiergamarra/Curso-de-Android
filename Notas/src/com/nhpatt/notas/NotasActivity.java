package com.nhpatt.notas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NotasActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevanota);
		//
		// final Button button = (Button) findViewById(R.id.salir);
		// button.setOnClickListener(this);
	}

	public void onClick(final View v) {
		final TextView textView = (TextView) findViewById(R.id.insertarNota);
		textView.setText(R.string.notaInsertada);
	}
}