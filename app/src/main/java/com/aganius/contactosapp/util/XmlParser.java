package com.aganius.contactosapp.util;


import android.content.Context;

import com.aganius.contactosapp.logica.Contacto;
import com.aganius.contactosapp.modelo.DatabaseHandler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XmlParser {

	public static void parseXML(Context applicationContext)
			throws XmlPullParserException, IOException {

		XmlPullParserFactory pullParserFactory;
		pullParserFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = pullParserFactory.newPullParser();

		try {

			InputStream is = applicationContext.getAssets().open(
					"contactos.xml");
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(is, null);

		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
		}

		int eventType = parser.getEventType();
		Contacto contactoActual = null;

		applicationContext.deleteDatabase("agenda_contactos");

		DatabaseHandler databaseHandler = new DatabaseHandler(applicationContext);

		while (eventType != XmlPullParser.END_DOCUMENT) {
			String name = null;
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				name = parser.getName();
				if (name.equals("contacto")) {
					contactoActual = new Contacto();
				} else if (contactoActual != null) {
					switch (name) {
						case "nombre":
							contactoActual.setNombre(parser.nextText());
							break;
						case "telefono":
							contactoActual.setTelefono(parser.nextText());
							break;
						case "email":
							contactoActual.setEmail(parser.nextText());
							break;
						case "direccion":
							contactoActual.setDireccion(parser.nextText());
							break;
					}
				}
				break;
			case XmlPullParser.END_TAG:
				name = parser.getName();
				if (name.equalsIgnoreCase("contacto") && contactoActual != null) {

					if (!contactoActual.getNombre().isEmpty() ||
							!contactoActual.getTelefono().isEmpty() ||
							!contactoActual.getEmail().isEmpty() ||
							!contactoActual.getDireccion().isEmpty()) {
						contactoActual.setFavorito(false);
						databaseHandler.agregarContacto(contactoActual);
					}

				}
			}
			eventType = parser.next();
		}

		databaseHandler.close();

	}

}
