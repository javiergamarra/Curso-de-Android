package com.nhpatt.servicios;

import java.util.HashMap;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.nhpatt.database.NotaDataBase;

public class MyProvider extends ContentProvider {
	public static final Uri CONTENT_URI = Uri
			.parse("content://com.nhpatt.actividades.NotasActivity/notas");

	public static final Uri SEARCH_URI = Uri
			.parse("content://com.nhpatt.actividades.NotasActivity/"
					+ SearchManager.SUGGEST_URI_PATH_QUERY);

	private NotaDataBase notaDataBase;

	@Override
	public boolean onCreate() {
		notaDataBase = new NotaDataBase(getContext());
		return true;
	}

	private static final int ALLROWS = 1;
	private static final int SINGLE_ROW = 2;
	private static final int SEARCH = 3;
	private static final UriMatcher uriMatcher;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.nhpatt.actividades.NotasActivity", "notas",
				ALLROWS);
		uriMatcher.addURI("com.nhpatt.actividades.NotasActivity", "notas/#",
				SINGLE_ROW);
		uriMatcher.addURI("com.nhpatt.actividades.NotasActivity",
				SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH);
		uriMatcher.addURI("com.nhpatt.actividades.NotasActivity",
				SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH);
		uriMatcher.addURI("com.nhpatt.actividades.NotasActivity",
				SearchManager.SUGGEST_URI_PATH_SHORTCUT, SEARCH);
		uriMatcher.addURI("com.nhpatt.actividades.NotasActivity",
				SearchManager.SUGGEST_URI_PATH_SHORTCUT + "/*", SEARCH);
	}

	private static final HashMap<String, String> SEARCH_PROJECTION_MAP;
	static {
		SEARCH_PROJECTION_MAP = new HashMap<String, String>();
		SEARCH_PROJECTION_MAP.put(SearchManager.SUGGEST_COLUMN_TEXT_1,
				NotaDataBase.DESCRIPCION + " AS "
						+ SearchManager.SUGGEST_COLUMN_TEXT_1);
		SEARCH_PROJECTION_MAP.put("_id", NotaDataBase.ID + " AS " + "_id");
	}

	@Override
	public Cursor query(final Uri uri, final String[] projection,
			final String selection, final String[] selectionArgs,
			final String sort) {
		final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(NotaDataBase.TABLA_NOTAS);
		switch (uriMatcher.match(uri)) {
		case SEARCH:
			if (uri.getPathSegments() != null
					&& uri.getPathSegments().size() > 1) {
				qb.appendWhere(NotaDataBase.DESCRIPCION + " LIKE \"%"
						+ uri.getPathSegments().get(1) + "%\"");
				qb.setProjectionMap(SEARCH_PROJECTION_MAP);
			}
			break;
		default:
			break;
		}

		notaDataBase.open();
		final Cursor c = qb.query(notaDataBase.getDataBase(), projection,
				selection, selectionArgs, null, null, null);
		return c;
	}

	@Override
	public String getType(final Uri _uri) {
		switch (uriMatcher.match(_uri)) {
		case ALLROWS:
			return "com.nhpatt.actividades.NotasActivity.notas/myprovidercontent";
		case SINGLE_ROW:
			return "com.nhpatt.actividades.NotasActivity.nota/myprovidercontent";
		case SEARCH:
			return SearchManager.SUGGEST_MIME_TYPE;
		default:
			throw new IllegalArgumentException("Unsupported URI: " + _uri);
		}
	}

	@Override
	public Uri insert(final Uri uri, final ContentValues values) {
		return null;
	}

	@Override
	public int delete(final Uri uri, final String selection,
			final String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(final Uri uri, final ContentValues values,
			final String selection, final String[] selectionArgs) {
		return 0;
	}

}
