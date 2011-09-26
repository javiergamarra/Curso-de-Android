package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWorld extends ListActivity implements OnClickListener {

	private static final String APPLICATION_TAG = "nhpattAPP";
	private final List<Nota> notas = new ArrayList<Nota>();
	private MyArrayAdapter adapter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button button = (Button) findViewById(R.id.incluirNota);
		button.setOnClickListener(this);

		final Button salir = (Button) findViewById(R.id.salir);
		salir.setOnClickListener(this);

		adapter = new MyArrayAdapter(this, R.layout.row, notas);
		setListAdapter(adapter);

		ListView lista = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(lista);

		Log.d(APPLICATION_TAG, "Creating activity...");
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.incluirNota:
			final TextView text = (TextView) findViewById(R.id.textoNota);
			String nota = text.getText().toString();
			notas.add(new Nota(nota));
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
		dialog.setMessage(notas.get(position).toString());
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
		MenuItem item = menu.add(0, Menu.FIRST, 0, "Sobre Lex Nova");
		item.setShortcut('0', 'b');
		item.setIcon(R.drawable.icon);
		item = menu.add(0, (Menu.FIRST) + 1, 0, "Preferencias");
		item.setShortcut('0', 'b');
		item.setIcon(R.drawable.icon);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		Intent intent;
		switch (item.getItemId()) {
		case Menu.FIRST:
			Toast.makeText(this, "Menú 1", Toast.LENGTH_SHORT).show();
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://lexnova.es"));
			startActivity(intent);
			return true;
		case Menu.FIRST + 1:
			Toast.makeText(this, "Menú 2", Toast.LENGTH_SHORT).show();
			intent = new Intent(this, Preferencias.class);
			startActivity(intent);
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, Menu.FIRST, 0, "Eliminar");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			String nota = (String) getListAdapter().getItem(info.position);
			notas.remove(nota);
			Toast.makeText(this, nota, Toast.LENGTH_SHORT).show();
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
		return false;
	}
}