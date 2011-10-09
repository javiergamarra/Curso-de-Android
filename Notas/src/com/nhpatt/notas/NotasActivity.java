package com.nhpatt.notas;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
		final String textoNota = campoNuevaNota.getText().toString();
		notas.add(textoNota);
		arrayAdapter.notifyDataSetChanged();
		Toast.makeText(this, "Nota insertada: " + textoNota, Toast.LENGTH_SHORT)
				.show();
		campoNuevaNota.setText("");
	}

	@Override
	protected void onListItemClick(final ListView l, final View v,
			final int position, final long id) {
		super.onListItemClick(l, v, position, id);
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setCancelable(true);
		alertDialog.setTitle("Nota seleccionada");

		alertDialog.setMessage(l.getAdapter().getItem(position).toString());
		alertDialog.setNeutralButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int which) {
					}
				});
		alertDialog.show();
	}
}