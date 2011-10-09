package com.nhpatt.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.nhpatt.actividades.NotasActivity;

public class ParseadorXML extends AsyncTask<Void, Integer, Void> {

	private static final String NHPATT_BLOG = "http://feeds.feedburner.com/nhpatt?format=xml";
	private static final String PARSEADOR_XML = "PARSEADOR_XML_NHPATT";
	private final List<String> titulos = new ArrayList<String>();
	private final NotasActivity actividad;
	private final ProgressDialog dialog;

	public ParseadorXML(final NotasActivity actividad) {
		dialog = new ProgressDialog((Context) actividad);
		dialog.setProgress(0);
		dialog.setMax(100);
		dialog.setTitle("Calculando...");
		dialog.show();
		this.actividad = actividad;
	}

	@Override
	protected Void doInBackground(final Void... params) {

		try {
			final URL url = new URL(NHPATT_BLOG);
			final HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				final InputStream in = httpURLConnection.getInputStream();
				final DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				final DocumentBuilder documentBuilder = dbf
						.newDocumentBuilder();

				final Document documento = documentBuilder.parse(in);

				final Element elementos = documento.getDocumentElement();

				final NodeList nodos = elementos.getElementsByTagName("title");
				if (nodos != null && nodos.getLength() > 0) {
					for (int i = 0; i < nodos.getLength(); i++) {
						final Element entry = (Element) nodos.item(i);
						titulos.add(entry.getFirstChild().getTextContent());
					}
				}
			}
			publishProgress(new Integer[] { 100 });
		} catch (final MalformedURLException e) {
			Log.e(PARSEADOR_XML, "URL incorrecta");
		} catch (final IOException e) {
			Log.e(PARSEADOR_XML, "Error de conexión");
		} catch (final ParserConfigurationException e) {
			Log.e(PARSEADOR_XML, "Error de configuración del parseador");
		} catch (final SAXException e) {
			Log.e(PARSEADOR_XML, "Error de parseo");
		}
		return null;
	}

	@Override
	protected void onPostExecute(final Void result) {
		dialog.dismiss();
		actividad.insertarResultado(titulos);
	}

}
