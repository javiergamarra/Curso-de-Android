package com.nhpatt.Hello;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nhpatt.model.Nota;

public class MyArrayAdapter extends ArrayAdapter<Nota> {

	public MyArrayAdapter(final Context context, final int resource,
			final List<Nota> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		if (convertView == null) {
			final LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.row, null);
		}
		final Nota nota = getItem(position);
		final TextView fecha = (TextView) convertView
				.findViewById(R.id.bottomText);
		fecha.setText(nota.getFechaCreacion().toGMTString());
		final TextView textoNota = (TextView) convertView
				.findViewById(R.id.topText);
		textoNota.setText(nota.getDescripcion());

		return convertView;
	}
}
