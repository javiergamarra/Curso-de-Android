package com.nhpatt.notas;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class NotasActivity extends ListActivity implements OnClickListener {

	private final List<String> notas = new ArrayList<String>();
	private ArrayAdapter<String> arrayAdapter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, notas);
		setListAdapter(arrayAdapter);

		final Button button = (Button) findViewById(R.id.incluirNuevaNota);
		button.setOnClickListener(this);
	}

	public void onClick(final View v) {
		final EditText campoNuevaNota = (EditText) findViewById(R.id.nuevaNota);
		notas.add(campoNuevaNota.getText().toString());
		arrayAdapter.notifyDataSetChanged();
		campoNuevaNota.setText("");
	}
}