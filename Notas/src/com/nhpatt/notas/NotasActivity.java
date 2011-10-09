package com.nhpatt.notas;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotasActivity extends Activity {
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Button button = new Button(this);
		button.setText("Salir");

		final TextView textView = new TextView(this);
		textView.setText("Inserta una nueva nota");

		final LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.addView(textView);
		linearLayout.addView(button);
		setContentView(linearLayout);

		// setContentView(R.layout.main);

	}
}