package com.nhpatt.util;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nhpatt.Hello.R;

public class Browser extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		WebView webView = (WebView) findViewById(R.id.browser);
		webView.loadUrl("www.google.es");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (!url.contains("google")) {
					return false;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}
}
