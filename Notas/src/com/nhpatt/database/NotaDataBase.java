package com.nhpatt.database;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.nhpatt.modelos.Nota;

//TODO Plantear si merece la pena la creación de otra capa adicional de BD
public class NotaDataBase {

	private SQLiteDatabase db;
	private final NotasDBOpenHelper dbHelper;

	public static final String ID = "_id";
	public static final String DESCRIPCION = "descripcion";
	public static final String FECHA = "fecha";
	public static final String TABLA_NOTAS = "notas";

	public NotaDataBase(final Context context) {
		dbHelper = new NotasDBOpenHelper(context);
	}

	public long insertar(final Nota nota) {
		final ContentValues valoresNota = new ContentValues();
		valoresNota.put(DESCRIPCION, nota.getDescripcion());
		valoresNota.put(FECHA, nota.getFecha().getTime());
		return db.insert(TABLA_NOTAS, null, valoresNota);
	}

	public boolean eliminarNota(final long id) {
		return db.delete(TABLA_NOTAS, ID + "=" + id, null) > 0;
	}

	public boolean actualizarNota(final long id, final String nuevaDescripcion) {
		final ContentValues valoresNota = new ContentValues();
		valoresNota.put(DESCRIPCION, nuevaDescripcion);
		return db.update(TABLA_NOTAS, valoresNota, ID + "=" + id, null) > 0;
	}

	public Cursor findAll() {
		return db.query(TABLA_NOTAS, new String[] { ID, DESCRIPCION, FECHA },
				null, null, null, null, null);
	}

	public Cursor findById(final long id) throws SQLException {
		final Cursor result = db.query(true, TABLA_NOTAS, new String[] { ID,
				DESCRIPCION }, ID + "=" + id, null, null, null, null, null);
		if (result.getCount() == 0 || !result.moveToFirst()) {
			throw new SQLException("No hay resultados para el id: " + id);
		}
		return result;
	}

	public Nota getNotaPorId(final long id) throws SQLException {
		final Cursor cursor = db.query(true, TABLA_NOTAS, new String[] { ID,
				DESCRIPCION }, ID + "=" + id, null, null, null, null, null);
		if (cursor.getCount() == 0 || !cursor.moveToFirst()) {
			throw new SQLException("No hay resultados para el id: " + id);
		}
		final String descripcion = cursor.getString(cursor
				.getColumnIndex(DESCRIPCION));
		final long fechaCreacion = cursor.getLong(cursor.getColumnIndex(FECHA));
		final Nota nota = new Nota(cursor.getInt(cursor.getColumnIndex(ID)),
				descripcion, new Date(fechaCreacion));
		return nota;
	}

	public void open() throws SQLiteException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (final SQLiteException ex) {
			db = dbHelper.getReadableDatabase();
		}
	}

	public SQLiteDatabase getDataBase() {
		return db;
	}

	public void close() {
		db.close();
	}

	public Nota crearNotaDeCursor(final Cursor cursor) {
		final int id = cursor.getInt(cursor.getColumnIndex(ID));
		final long fecha = cursor.getLong(cursor.getColumnIndex(FECHA));
		final String descripcion = cursor.getString(cursor
				.getColumnIndex(DESCRIPCION));
		return new Nota(id, descripcion, new Date(fecha));
	}

}
