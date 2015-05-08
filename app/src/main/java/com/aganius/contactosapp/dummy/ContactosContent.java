package com.aganius.contactosapp.dummy;

import com.aganius.contactosapp.logica.Contacto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactosContent {

    /**
     * An array of sample (contactos) items.
     */
    public static List<Contacto> CONTACTOS = new ArrayList<>();

    /**
     * A map of sample (contactos) items, by ID.
     */
    public static Map<String, Contacto> CONTACTOS_MAP = new HashMap<>();

//    static {
//        // Add 3 sample items.
//        addItem(new Contacto("Johan", "3014788368", "johan@test.com", "Calle Invisible", false));
//    }

    private static void addItem(Contacto contacto) {
        CONTACTOS.add(contacto);
        CONTACTOS_MAP.put(contacto.getNombre(), contacto);
    }
}
