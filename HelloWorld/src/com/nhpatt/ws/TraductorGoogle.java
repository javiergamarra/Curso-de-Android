package com.nhpatt.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TraductorGoogle {
	private static final String TAG = "TraductorGoogle";

	public static String traducir(String textoParaTraducir,
			String idiomaOrigen, String idiomaDestino) {

		String textoTraducido = null;
		HttpURLConnection con = null;
		Log.d(TAG, "traducir(" + textoParaTraducir + ", " + idiomaOrigen + ", "
				+ idiomaDestino + ")");

		try {
			String q = URLEncoder.encode(textoParaTraducir, "UTF-8");
			URL url = new URL(
					"http://ajax.googleapis.com/ajax/services/language/translate"
							+ "?v=1.0" + "&q=" + q + "&langpair="
							+ idiomaOrigen + "%7C" + idiomaDestino);
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10000);
			con.setConnectTimeout(15000);
			con.setRequestMethod("GET");
			con.addRequestProperty("Referer", "http://lexnova.es");
			con.setDoInput(true);

			con.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String payload = reader.readLine();
			reader.close();

			JSONObject jsonObject = new JSONObject(payload);
			textoTraducido = jsonObject.getJSONObject("responseData")
					.getString("translatedText").replace("&#39;", "'")
					.replace("&amp;", "&");

		} catch (IOException e) {
			Log.e(TAG, "IOException", e);
		} catch (JSONException e) {
			Log.e(TAG, "JSONException", e);
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		return textoTraducido;
	}

}
