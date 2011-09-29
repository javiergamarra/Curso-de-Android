package com.nhpatt.Hello;

import android.app.ListActivity;
import android.app.SearchManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class NotasSearch extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String searchTerm = getIntent()
				.getStringExtra(SearchManager.USER_QUERY);
		Uri searchQuery = Uri.withAppendedPath(MyProvider.SEARCH_URI,
				searchTerm);
		Cursor cursor = getContentResolver().query(searchQuery, null, null,
				null, null);
		startManagingCursor(cursor);
		SimpleCursorAdapter searchResults = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor,
				new String[] { SearchManager.SUGGEST_COLUMN_TEXT_1 },
				new int[] { android.R.id.text1 });
		setListAdapter(searchResults);
	}

}
