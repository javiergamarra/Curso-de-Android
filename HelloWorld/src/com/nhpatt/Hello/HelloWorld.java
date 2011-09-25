package com.nhpatt.Hello;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends Activity implements OnClickListener {

	private final List<String> notas = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button = (Button) findViewById(R.id.enlace);
		button.setOnClickListener(this);
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

	public void onClick(final View v) {
		final TextView text = (TextView) findViewById(R.id.text);
		notas.add((String) text.getText());
	}

}