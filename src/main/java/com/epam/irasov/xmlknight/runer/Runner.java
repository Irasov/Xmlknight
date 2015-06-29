package com.epam.irasov.xmlknight.runer;

import com.epam.irasov.xmlknight.entity.*;
import com.epam.irasov.xmlknight.logic.Action;
import com.epam.irasov.xmlknight.parser.AbstractParser;
import com.epam.irasov.xmlknight.parser.KnightParserFactory;
import com.epam.irasov.xmlknight.parser.KnightParserSAX;
import com.epam.irasov.xmlknight.reporter.Report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Runner {
    public static final String CUSTOM_SORT_CRITERION = "weight";
    public static final int CUSTOM_MIN_PRICE = 500;
    public static final int CUSTOM_MAX_PRICE = 1500;

    public static void main(String[] args) throws IOException {
        KnightParserFactory knightParserFactory = new KnightParserFactory();
        AbstractParser parser = knightParserFactory.createKnightParser("sax");
        InputStream inputStream = Runner.class.getClassLoader().getResourceAsStream("knight.xml");
        Knight knight = parser.knightParser(inputStream);
        Action.sortAmmunition(knight.getAmmunitionList(), CUSTOM_SORT_CRITERION);
        Report.report(knight);
        Report.report(Action.searchPrice(knight.getAmmunitionList(), CUSTOM_MIN_PRICE, CUSTOM_MAX_PRICE));

       /* AbstractParser parser2 = knightParserFactory.createKnightParser("stax");
        Knight knight1 = parser2.knightParser(inputStream);
        System.out.println(knight1.toString());*/
    }
}
