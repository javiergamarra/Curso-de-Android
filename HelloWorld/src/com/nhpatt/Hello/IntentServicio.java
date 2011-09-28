package com.nhpatt.Hello;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class IntentServicio extends IntentService {

	public IntentServicio() {
		super("IntentServicio");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Servicio 2", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		long endTime = System.currentTimeMillis() + 5 * 1000;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}
	}
}
