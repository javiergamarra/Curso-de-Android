package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends ListActivity implements OnClickListener {

	private static final String APPLICATION_TAG = "nhpattAPP";
	private final List<String> notas = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button = (Button) findViewById(R.id.incluirNota);
		button.setOnClickListener(this);
		final Button salir = (Button) findViewById(R.id.salir);
		salir.setOnClickListener(this);
		Log.d(APPLICATION_TAG, "Creating activity...");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, notas);
		setListAdapter(adapter);
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.incluirNota:
			final TextView text = (TextView) findViewById(R.id.textoNota);
			notas.add(text.getText().toString());
			text.setText("");
			adapter.notifyDataSetChanged();
			break;
		case R.id.salir:
			finish();
		default:
			break;
		}
	}

}