package com.nhpatt.util;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nhpatt.model.Nota;

public class NotaDataBase {

	private static final String DATABASE_NAME = "todoList.db";
	private static final String DATABASE_TABLE = "todoItems";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase db;
	private final Context context;

	public static final String KEY_ID = "_id";
	public static final String KEY_TASK = "task";
	public static final String KEY_CREATION_DATE = "creation_date";

	private final NotaDBOpenHelper dbHelper;

	public NotaDataBase(Context _context) {
		this.context = _context;
		dbHelper = new NotaDBOpenHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public long insertar(Nota nota) {
		ContentValues valoresNota = new ContentValues();
		valoresNota.put(KEY_TASK, nota.getNota());
		valoresNota.put(KEY_CREATION_DATE, nota.getFechaCreacion().getTime());
		return db.insert(DATABASE_TABLE, null, valoresNota);
	}

	public boolean eliminarNota(long id) {
		return db.delete(DATABASE_TABLE, KEY_ID + "=" + id, null) > 0;
	}

	public boolean actualizarNota(long id, String nuevaDescripcion) {
		ContentValues newValue = new ContentValues();
		newValue.put(KEY_TASK, nuevaDescripcion);
		return db.update(DATABASE_TABLE, newValue, KEY_ID + "=" + id, null) > 0;
	}

	public Cursor findAll() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_TASK,
				KEY_CREATION_DATE }, null, null, null, null, null);
	}

	public Cursor findById(long id) throws SQLException {
		Cursor result = db.query(true, DATABASE_TABLE, new String[] { KEY_ID,
				KEY_TASK }, KEY_ID + "=" + id, null, null, null, null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) {
			throw new SQLException("No to do items found for row: " + id);
		}
		return result;
	}

	public Nota getNotaPorId(long id) throws SQLException {
		Cursor cursor = db.query(true, DATABASE_TABLE, new String[] { KEY_ID,
				KEY_TASK }, KEY_ID + "=" + id, null, null, null, null, null);
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			throw new SQLException("No to do item found for row: " + id);
		}
		String descripcion = cursor.getString(cursor.getColumnIndex(KEY_TASK));
		long fechaCreacion = cursor.getLong(cursor
				.getColumnIndex(KEY_CREATION_DATE));
		Nota nota = new Nota(descripcion, new Date(fechaCreacion));
		return nota;
	}

	public void open() throws SQLiteException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			db = dbHelper.getReadableDatabase();
		}
	}

	public void close() {
		db.close();
	}

	private static class NotaDBOpenHelper extends SQLiteOpenHelper {
		public NotaDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		private static final String DATABASE_CREATE = "create table "
				+ DATABASE_TABLE + " (" + KEY_ID
				+ " integer primary key autoincrement, " + KEY_TASK
				+ " text not null, " + KEY_CREATION_DATE + " long);";

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int _oldVersion,
				int _newVersion) {
			Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion
					+ " to " + _newVersion
					+ ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

}
