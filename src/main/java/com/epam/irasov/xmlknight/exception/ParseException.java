package com.epam.irasov.xmlknight.exception;

import org.apache.log4j.Logger;

public class ParseException extends RuntimeException {
    private static Logger LOGGER = Logger.getLogger(ParseException.class);
    public ParseException(Throwable cause){
        LOGGER.info(cause);
    }

    public ParseException(String message,Throwable cause){
        LOGGER.info(message);
        LOGGER.info(cause);
    }

    public ParseException(String message){
        LOGGER.info(message);
    }
    public ParseException(){

    }
}
