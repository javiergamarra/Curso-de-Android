package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends ListActivity implements OnClickListener {

	private final List<String> notas = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

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
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, notas);
		setListAdapter(adapter);
	}

	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.enlace:
			final TextView text = (TextView) findViewById(R.id.text);
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