package com.epam.irasov.xmlknight.util;

import java.util.*;

public class PropertyManagerBodyTag extends AbstractPropertyManager {

    public List<String> getPropertyBodyTag() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<String> tagBodyList = new ArrayList<>();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
           tagBodyList.add(value);
        }
        return tagBodyList;
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
