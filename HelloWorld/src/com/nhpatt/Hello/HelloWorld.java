package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends Activity implements OnClickListener {

	private final List<String> notas = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button = (Button) findViewById(R.id.enlace);
		button.setOnClickListener(this);
		final Button salir = (Button) findViewById(R.id.salir);
		salir.setOnClickListener(this);
		System.out.println("Creating activity...");
	}

	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.enlace:
			final TextView text = (TextView) findViewById(R.id.text);
			notas.add(text.getText().toString());
			break;
		case R.id.salir:
			finish();
		default:
			break;
		}
	}

}