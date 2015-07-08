package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.exception.ParseException;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ValidatorScheme {
    private static Logger LOGGER = Logger.getLogger(ValidatorScheme.class);

    public static void validator(){

        URL xsdUrl = ValidatorScheme.class.getClassLoader().getResource("knight.xsd");
        InputStream xmlIn = ValidatorScheme.class.getClassLoader().getResourceAsStream("knight.xml");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            assert xsdUrl != null;
            schema = schemaFactory.newSchema(xsdUrl);
        } catch (SAXException e) {
            throw new ParseException(e);
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setSchema(schema);

        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(xmlIn, new MyHandler());
        } catch (ParserConfigurationException | SAXException|IOException e) {
            throw new ParseException(e);
        }
        LOGGER.info("successfully tested scheme");

    }

    private  static class MyHandler extends DefaultHandler implements ErrorHandler {

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            LOGGER.info("Warning: "+exception);
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            LOGGER.info("ERROR: "+exception);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            LOGGER.info("FatalError: "+exception);
        }
    }
}
