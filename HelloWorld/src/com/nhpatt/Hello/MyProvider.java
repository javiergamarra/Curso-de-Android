package com.nhpatt.Hello;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.nhpatt.util.NotaDataBase;

public class MyProvider extends ContentProvider {
	private static final String myURI = "content://com.nhpatt.Hello.AndroidCourse/notas";
	public static final Uri CONTENT_URI = Uri.parse(myURI);

	private NotaDataBase notaDataBase;

	@Override
	public boolean onCreate() {
		notaDataBase = new NotaDataBase(this.getContext());
		return true;
	}

	private static final int ALLROWS = 1;
	private static final int SINGLE_ROW = 2;
	private static final UriMatcher uriMatcher;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse", "notas", ALLROWS);
		uriMatcher.addURI("com.nhpatt.Hello.AndroidCourse", "notas/#",
				SINGLE_ROW);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sort) {
		// If this is a row query, limit the result set to the passed in row.
		switch (uriMatcher.match(uri)) {
		case SINGLE_ROW:
		case ALLROWS:
			notaDataBase.open();
			return notaDataBase.findAll();
		}
		return null;
	}

	@Override
	public String getType(Uri _uri) {
		switch (uriMatcher.match(_uri)) {
		case ALLROWS:
			return "com.nhpatt.Hello.AndroidCourse.notas/myprovidercontent";
		case SINGLE_ROW:
			return "com.nhpatt.Hello.AndroidCourse.nota/myprovidercontent";
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
