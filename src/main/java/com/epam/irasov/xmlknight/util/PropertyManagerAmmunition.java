package com.epam.irasov.xmlknight.util;

import com.epam.irasov.xmlknight.entity.Ammunition;
import com.epam.irasov.xmlknight.entity.NamedEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertyManagerAmmunition extends AbstractPropertyManager {
    
    public  Map<String, String> getPropertyAmmunition() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, String> ammunitionMap = new HashMap<>();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            String k = key.substring(0,key.indexOf("."));
            ammunitionMap.put(key.substring(0,key.indexOf(".")), value);
        }
        return ammunitionMap;
    }
}
