package com.nhpatt.util;

import java.util.List;

import com.nhpatt.Hello.R;
import com.nhpatt.Hello.R.id;
import com.nhpatt.Hello.R.layout;
import com.nhpatt.model.Nota;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Nota> {

	public MyArrayAdapter(Context context, int resource, List<Nota> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.row, null);
		}
		Nota nota = getItem(position);
		TextView fecha = (TextView) convertView.findViewById(R.id.bottomText);
		fecha.setText(nota.getFechaCreacion().toGMTString());
		TextView textoNota = (TextView) convertView.findViewById(R.id.topText);
		textoNota.setText(nota.getNota());

		return convertView;
	}
}
