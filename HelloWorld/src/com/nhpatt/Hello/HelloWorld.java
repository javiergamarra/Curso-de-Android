package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, notas);
		setListAdapter(adapter);
		Log.d(APPLICATION_TAG, "Creating activity...");
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.incluirNota:
			final TextView text = (TextView) findViewById(R.id.textoNota);
			String nota = text.getText().toString();
			notas.add(nota);
			Toast.makeText(this, "Añadida la nota: " + nota, Toast.LENGTH_LONG)
					.show();
			text.setText("");
			adapter.notifyDataSetChanged();
			break;
		case R.id.salir:
			finish();
		default:
			break;
		}
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Descripción de nota");
		dialog.setMessage(notas.get(position));
		dialog.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, Menu.FIRST, 0, "blah");
		item.setShortcut('0', 'b');
		item.setIcon(R.drawable.icon);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case Menu.FIRST:
			Toast.makeText(this, "Menú", Toast.LENGTH_SHORT).show();
			return true;
		default:
			break;
		}
		return false;
	}
}