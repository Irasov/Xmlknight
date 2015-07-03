package com.epam.irasov.xmlknight.util;

import com.epam.irasov.xmlknight.entity.Ammunition;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertyManager {
    private static final String FILE_NAME_PROPERTIES = "ammunition.properties";
    Properties properties = new Properties();

    public void loadProperty() {
        try {
            InputStream inputStream = PropertyManager.class.getClassLoader().getResourceAsStream(FILE_NAME_PROPERTIES);
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Map<String, Ammunition> getPropertyAmmunition() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, Ammunition> ammunitionMap = new HashMap<>();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            String k = key.substring(0,key.indexOf("."));
            Class cl = Class.forName(value);
            ammunitionMap.put(key.substring(0,key.indexOf(".")), (Ammunition) cl.newInstance());
        }
        return ammunitionMap;
    }

}
