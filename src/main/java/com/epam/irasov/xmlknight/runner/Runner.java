package com.epam.irasov.xmlknight.runner;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.logic.Action;
import com.epam.irasov.xmlknight.parser.DOMCreatorXML;
import com.epam.irasov.xmlknight.parser.Parser;
import com.epam.irasov.xmlknight.parser.KnightParserFactory;
import com.epam.irasov.xmlknight.parser.ValidatorScheme;
import com.epam.irasov.xmlknight.reporter.Report;
import com.epam.irasov.xmlknight.util.FileOperation;

public class Runner {
    public static final String CUSTOM_SORT_CRITERION = "weight";
    public static final int CUSTOM_MIN_PRICE = 500;
    public static final int CUSTOM_MAX_PRICE = 1500;
    public static final String XML_FILE = "knight.xml";
    public static final String CUSTOM_PARSER = "jaxb";

    public static void main(String[] args) {
        ValidatorScheme.validator();
        DOMCreatorXML.creator();
        KnightParserFactory knightParserFactory = new KnightParserFactory();
        Parser parser = knightParserFactory.createKnightParser(CUSTOM_PARSER);
        Knight knight = parser.knightParser(FileOperation.load(XML_FILE));
        Action.sortAmmunition(knight.getAmmunitionList(), CUSTOM_SORT_CRITERION);
        Report.report(knight);
        Report.report(Action.searchPrice(knight.getAmmunitionList(), CUSTOM_MIN_PRICE, CUSTOM_MAX_PRICE));
    }
}
