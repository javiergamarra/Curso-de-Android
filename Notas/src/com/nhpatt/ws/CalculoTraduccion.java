package com.nhpatt.ws;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.nhpatt.actividades.NotasActivity;
import com.nhpatt.modelos.Nota;

public class CalculoTraduccion {

	private final NotasActivity notasActivity;
	private final Handler handler = new Handler();
	private String descripcionTraducida;
	private final Nota nota;

	public CalculoTraduccion(final NotasActivity notasActivity, final Nota nota) {
		this.notasActivity = notasActivity;
		this.nota = nota;
		new Thread(null, doBackgroundThreadProcessing, "Hilo de traducción")
				.start();
	}

	private final Runnable doBackgroundThreadProcessing = new Runnable() {
		public void run() {
			final SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(notasActivity
							.getApplicationContext());
			descripcionTraducida = TraductorGoogle.traducir(
					nota.getDescripcion(), "ES",
					preferences.getString("PREF_TRADUCTOR_IDIOMAS", "EN"));
			handler.post(doUpdateGUI);
		}
	};

	private final Runnable doUpdateGUI = new Runnable() {
		public void run() {
			Toast.makeText(notasActivity, descripcionTraducida,
					Toast.LENGTH_SHORT).show();
		}
	};

}
