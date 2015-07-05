package com.epam.irasov.xmlknight.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractPropertyManager {
    Properties properties = new Properties();

    public void loadProperty(InputStream inputStream) {
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
