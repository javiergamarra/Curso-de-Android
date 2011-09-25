package com.nhpatt.Hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelloWorld extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LinearLayout linearLayout = new LinearLayout(this);
		final Button button = new Button(this);
		button.setText(R.string.enlace);
		button.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		final TextView textView = new TextView(this);
		textView.setText(R.string.nota);
		linearLayout.addView(textView, new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		linearLayout.addView(button, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(linearLayout);
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