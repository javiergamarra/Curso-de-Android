package com.nhpatt.notas;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
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

		Button button = (Button) findViewById(R.id.incluirNuevaNota);
		button.setOnClickListener(this);

		button = (Button) findViewById(R.id.salir);
		button.setOnClickListener(this);

		final ListView lista = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(lista);

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		super.onCreateOptionsMenu(menu);
		final MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menuprincipal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		Toast.makeText(this, "Ha seleccionado un menú", Toast.LENGTH_SHORT)
				.show();
		return true;
	}

	@Override
	public void onCreateContextMenu(final ContextMenu menu, final View v,
			final ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contextmenu, menu);
	}

	@Override
	public boolean onContextItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.eliminarNota:
			eliminarNota(item);
			break;
		default:
			break;
		}
		return true;
	}

	private void eliminarNota(final MenuItem item) {
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		notas.remove(getListAdapter().getItem(info.position));
		arrayAdapter.notifyDataSetChanged();
	}

	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.salir:
			finish();
			break;
		case R.id.incluirNuevaNota:
			incluirNuevaNota();
		default:
			break;
		}
	}

	private void incluirNuevaNota() {
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