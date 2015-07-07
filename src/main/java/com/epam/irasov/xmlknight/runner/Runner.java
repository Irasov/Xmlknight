package com.epam.irasov.xmlknight.runner;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.logic.Action;
import com.epam.irasov.xmlknight.parser.Parser;
import com.epam.irasov.xmlknight.parser.KnightParserFactory;
import com.epam.irasov.xmlknight.reporter.Report;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class Runner {
    public static final String CUSTOM_SORT_CRITERION = "weight";
    public static final int CUSTOM_MIN_PRICE = 500;
    public static final int CUSTOM_MAX_PRICE = 1500;
    public static final String XML_FILE = "knight.xml";
    public static final String CUSTOM_SAX_PARSER = "sax";
    public static final String CUSTOM_STAX_PARSER = "stax";
    private static Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        KnightParserFactory knightParserFactory = new KnightParserFactory();
       // Parser parser = knightParserFactory.createKnightParser(CUSTOM_SAX_PARSER);
       Parser parser = knightParserFactory.createKnightParser(CUSTOM_STAX_PARSER);
        try {
            InputStream inputStream = Runner.class.getClassLoader().getResourceAsStream(XML_FILE);
            Knight knight = parser.knightParser(inputStream);
            Action.sortAmmunition(knight.getAmmunitionList(), CUSTOM_SORT_CRITERION);
            Report.report(knight);
            Report.report(Action.searchPrice(knight.getAmmunitionList(), CUSTOM_MIN_PRICE, CUSTOM_MAX_PRICE));
        }catch (IllegalArgumentException e){
            LOGGER.info(e);
        }
    }
}
