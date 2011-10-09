package com.nhpatt.notas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nhpatt.modelos.Nota;

public class NotasActivity extends ListActivity {

	private static final String URL_PRUEBA = "http://www.nhpatt.com";
	private static final int ACTIVIDAD_NUEVA_NOTA = 0;
	private final List<Nota> notas = new ArrayList<Nota>();
	private ArrayAdapter<Nota> arrayAdapter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		arrayAdapter = new ArrayAdapter<Nota>(this,
				android.R.layout.simple_list_item_1, notas);
		setListAdapter(arrayAdapter);

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
		switch (item.getItemId()) {
		case R.id.menuConocerMas:
			final Intent intentBrowser = new Intent(Intent.ACTION_VIEW,
					Uri.parse(URL_PRUEBA));
			startActivity(intentBrowser);
			return true;
		case R.id.menuPreferencias:
			final Intent actividadPreferencias = new Intent(this,
					PreferenciasActivity.class);
			startActivity(actividadPreferencias);
			return true;
		case R.id.menuSalir:
			finish();
			return true;
		case R.id.menuNuevaNota:
			final Intent actividadNuevaNota = new Intent(this,
					InsertarNotaActivity.class);
			startActivityForResult(actividadNuevaNota, ACTIVIDAD_NUEVA_NOTA);
			return true;
		}
		return false;
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

	@Override
	protected void onActivityResult(final int requestCode,
			final int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ACTIVIDAD_NUEVA_NOTA:
			if (Activity.RESULT_OK == resultCode) {
				final Nota nota = (Nota) data
						.getSerializableExtra(InsertarNotaActivity.NOTA);
				notas.add(nota);
				arrayAdapter.notifyDataSetChanged();
				Toast.makeText(this, "Nota insertada: " + nota.toString(),
						Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}

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