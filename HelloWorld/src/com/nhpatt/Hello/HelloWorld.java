package com.nhpatt.Hello;

import android.app.Activity;
import android.os.Bundle;

public class HelloWorld extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		System.out.println("Creating activity...");
	}

	@Override
	public void onStart() {
		super.onStart();
		System.out.println("Starting an activity...");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("Resuming an activity...");
	}

	@Override
	public void onPause() {
		super.onStop();
		System.out.println("Pausing an activity...");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("Stoping an activity...");
	}

}