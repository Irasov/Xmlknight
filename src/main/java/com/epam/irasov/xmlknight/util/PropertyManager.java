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
        List<String> prop = new ArrayList<>();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            System.out.println("xxx:"+value);
            prop.add(value);
        }
        String value1;
        String value2;
        int ii = 0;
        for (int i = 1; i < prop.size() - 1; i++) {
            value1 = prop.get(ii);
            value2 = prop.get(ii+1);
            System.out.println(value1);
            System.out.println(value2);
            ii=ii+2;
            Class cl = Class.forName(value2);
            ammunitionMap.put(value1, (Ammunition) cl.newInstance());
        }
        return ammunitionMap;
    }

}
