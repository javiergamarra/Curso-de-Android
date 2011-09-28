package com.nhpatt.util;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.nhpatt.Hello.HelloWorld;
import com.nhpatt.Hello.R;

public class Preferencias extends PreferenceActivity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Toast.makeText(
				this,
				preferences.getString(HelloWorld.VALOR_URL,
						HelloWorld.VALOR_URL_DEFECTO), Toast.LENGTH_SHORT)
				.show();
		addPreferencesFromResource(R.xml.preferences);
	}
}
