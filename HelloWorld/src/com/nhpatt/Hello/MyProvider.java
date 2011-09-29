package com.nhpatt.Hello;

import java.util.HashMap;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.nhpatt.util.NotaDataBase;

public class MyProvider extends ContentProvider {
	public static final Uri CONTENT_URI = Uri
			.parse("content://com.nhpatt.Hello.AndroidCourse/notas");

	public static final Uri SEARCH_URI = Uri
			.parse("content://com.nhpatt.Hello.AndroidCourse/"
					+ SearchManager.SUGGEST_URI_PATH_QUERY);

	private NotaDataBase notaDataBase;

	@Override
	public boolean onCreate() {
		notaDataBase = new NotaDataBase(this.getContext());
		return true;
	}

	private static final int ALLROWS = 1;
	private static final int SINGLE_ROW = 2;
	private static final int SEARCH = 3;
	private static final UriMatcher uriMatcher;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse", "notas", ALLROWS);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse", "notas/#",
				SINGLE_ROW);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse",
				SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse",
				SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse",
				SearchManager.SUGGEST_URI_PATH_SHORTCUT, SEARCH);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse",
				SearchManager.SUGGEST_URI_PATH_SHORTCUT + "/*", SEARCH);
	}

	private static final HashMap<String, String> SEARCH_PROJECTION_MAP;
	static {
		SEARCH_PROJECTION_MAP = new HashMap<String, String>();
		SEARCH_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_TEXT_1,
				NotaDataBase.DESCRIPCION_COLUMN + " AS "
						+ SearchManager.SUGGEST_COLUMN_TEXT_1);
		SEARCH_PROJECTION_MAP.put("_id", NotaDataBase.KEY_ID + " AS " + "_id");
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sort) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(NotaDataBase.DATABASE_TABLE);
		switch (uriMatcher.match(uri)) {
		case SEARCH:
			qb.appendWhere(NotaDataBase.DESCRIPCION_COLUMN + " LIKE \"%"
					+ uri.getPathSegments().get(1) + "%\"");
			qb.setProjectionMap(SEARCH_PROJECTION_MAP);
			break;
		default:
			break;
		}

		notaDataBase.open();
		Cursor c = qb.query(notaDataBase.getDb(), projection, selection,
				selectionArgs, null, null, null);
		return c;
	}

	@Override
	public String getType(Uri _uri) {
		switch (uriMatcher.match(_uri)) {
		case ALLROWS:
			return "com.nhpatt.Hello.AndroidCourse.notas/myprovidercontent";
		case SINGLE_ROW:
			return "com.nhpatt.Hello.AndroidCourse.nota/myprovidercontent";
		case SEARCH:
			return SearchManager.SUGGEST_MIME_TYPE;
		default:
			throw new IllegalArgumentException("Unsupported URI: " + _uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
