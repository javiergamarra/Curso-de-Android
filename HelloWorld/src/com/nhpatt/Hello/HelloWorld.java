package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import com.nhpatt.model.Nota;
import com.nhpatt.util.MyArrayAdapter;
import com.nhpatt.util.NotaDataBase;
import com.nhpatt.util.Preferencias;
import com.nhpatt.ws.ParseadorXML;
import com.nhpatt.ws.TraductorGoogle;

public class HelloWorld extends ListActivity implements OnClickListener {

	private static final String APPLICATION_TAG = "nhpattAPP";
	private final List<Nota> notas = new ArrayList<Nota>();
	private MyArrayAdapter adapter;
	private NotaDataBase dataBase;
	private Cursor cursor;

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

		dataBase = new NotaDataBase(this);
		dataBase.open();

		cursor = dataBase.findAll();
		startManagingCursor(cursor);
		cursor.requery();
		notas.clear();
		if (cursor.moveToFirst()) {
			do {
				String task = cursor.getString(cursor
						.getColumnIndex(NotaDataBase.KEY_TASK));
				long created = cursor.getLong(cursor
						.getColumnIndex(NotaDataBase.KEY_CREATION_DATE));
				Nota newItem = new Nota(task, new Date(created));
				notas.add(newItem);
			} while (cursor.moveToNext());
		}
		adapter.notifyDataSetChanged();

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
			dataBase.insertar(notas.get(notas.size() - 1));
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
		item.setIcon(R.drawable.icon);
		item = menu.add(0, (Menu.FIRST) + 1, 0, "Preferencias");
		item.setIcon(R.drawable.icon);
		item = menu.add(0, (Menu.FIRST) + 2, 0, "XML");
		item.setIcon(R.drawable.icon);
		item = menu.add(0, (Menu.FIRST) + 3, 0, "Browser");
		item.setIcon(R.drawable.icon);
		item = menu.add(0, (Menu.FIRST) + 4, 0, "Notificaciones");
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
		case Menu.FIRST + 2:
			ParseadorXML parseadorXML = new ParseadorXML();
			parseadorXML.recogerValores();
			Toast.makeText(this, "Menú 3", Toast.LENGTH_SHORT).show();
			return true;
		case Menu.FIRST + 3:
			intent = new Intent(this, com.nhpatt.util.Browser.class);
			startActivity(intent);
			return true;
		case Menu.FIRST + 4:
			Notification notification = new Notification(R.drawable.icon,
					"Notification corta", System.currentTimeMillis());
			intent = new Intent(this, Preferencias.class);
			PendingIntent launchIntent = PendingIntent.getActivity(
					getApplicationContext(), 0, intent, 0);
			notification.flags = notification.flags
					| Notification.FLAG_ONGOING_EVENT
					| Notification.DEFAULT_VIBRATE;

			notification.setLatestEventInfo(getApplicationContext(), "Titulo",
					"Texto largo", launchIntent);

			NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.notify(1, notification);

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
		menu.add(0, Menu.FIRST + 1, 0, "Traducir");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info;
		switch (item.getItemId()) {
		case Menu.FIRST:
			info = (AdapterContextMenuInfo) item.getMenuInfo();
			Nota nota = (Nota) getListAdapter().getItem(info.position);
			notas.remove(nota);
			Toast.makeText(this, nota.toString(), Toast.LENGTH_SHORT).show();
			adapter.notifyDataSetChanged();
			break;
		case Menu.FIRST + 1:
			info = (AdapterContextMenuInfo) item.getMenuInfo();
			Nota notaATraducir = (Nota) getListAdapter().getItem(info.position);
			TraductorGoogle traductorGoogle = new TraductorGoogle();
			Toast.makeText(
					this,
					traductorGoogle.traducir(notaATraducir.toString(), "ES",
							"en"), Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return false;
	}
}