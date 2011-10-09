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

import android.util.Log;

public class ParseadorXML {

	private static final String NHPATT_BLOG = "http://feeds.feedburner.com/nhpatt?format=xml";
	private static final String PARSEADOR_XML = "PARSEADOR_XML_NHPATT";

	public static List<String> recogerTitulosBlog() {
		final List<String> titulos = new ArrayList<String>();

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

		} catch (final MalformedURLException e) {
			Log.e(PARSEADOR_XML, "URL incorrecta");
		} catch (final IOException e) {
			Log.e(PARSEADOR_XML, "Error de conexión");
		} catch (final ParserConfigurationException e) {
			Log.e(PARSEADOR_XML, "Error de configuración del parseador");
		} catch (final SAXException e) {
			Log.e(PARSEADOR_XML, "Error de parseo");
		}
		return titulos;
	}

}
