package com.nhpatt.actividades;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Browser extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		final WebView webView = (WebView) findViewById(R.id.browser);
		webView.loadUrl("http://www.nhpatt.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(final WebView view,
					final String url) {
				if (!url.contains("google")) {
					return false;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}
}
