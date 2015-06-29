package com.epam.irasov.xmlknight.exception;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class KnightErrorHandler extends DefaultHandler{
    public KnightErrorHandler(SAXParseException e){
        e.getMessage();
    }

    }