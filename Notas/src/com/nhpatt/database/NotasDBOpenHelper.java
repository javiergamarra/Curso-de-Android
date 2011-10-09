package com.nhpatt.database;

import static com.nhpatt.database.NotaDataBase.DESCRIPCION;
import static com.nhpatt.database.NotaDataBase.FECHA;
import static com.nhpatt.database.NotaDataBase.ID;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotasDBOpenHelper extends SQLiteOpenHelper {

	public static final String BASE_DE_DATOS = "nota.db";
	public static final int VERSION = 2;

	private static final String CREAR_TABLA_NOTAS = "create table "
			+ NotaDataBase.TABLA_NOTAS + " (" + ID
			+ " integer primary key autoincrement, " + DESCRIPCION
			+ " text not null, " + FECHA + " long);";

	public NotasDBOpenHelper(final Context context) {
		super(context, BASE_DE_DATOS, null, VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(CREAR_TABLA_NOTAS);
		// db.execSQL(CREAR_TABLA_ALTERNATIVA);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int _oldVersion,
			final int _newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + NotaDataBase.TABLA_NOTAS);
		onCreate(db);
	}
}