package com.epam.irasov.xmlknight.util;

import org.apache.log4j.Logger;

import java.io.InputStream;

public class FileOperation {
    private static Logger LOGGER = Logger.getLogger(FileOperation.class);

    public static InputStream LoadProperty(String filename) {
        InputStream in = null;
        try {
             in = FileOperation.class.getClassLoader().getResourceAsStream(filename);
            return in;
        } catch (IllegalArgumentException e) {
            LOGGER.info("FILE NOT FOUND" + e);
        }
        return in;
    }
}