package com.epam.irasov.xmlknight.runer;

import com.epam.irasov.xmlknight.entity.Knight;
import com.epam.irasov.xmlknight.logic.Action;
import com.epam.irasov.xmlknight.parser.Parser;
import com.epam.irasov.xmlknight.parser.ParserFactoryKnight;
import com.epam.irasov.xmlknight.reporter.Report;

import java.io.IOException;
import java.io.InputStream;

public class Runner {
    public static final String CUSTOM_SORT_CRITERION = "weight";
    public static final int CUSTOM_MIN_PRICE = 500;
    public static final int CUSTOM_MAX_PRICE = 1500;

    public static void main(String[] args) throws IOException {
        ParserFactoryKnight knightParserFactory = new ParserFactoryKnight();
        Parser parser = knightParserFactory.createKnightParser("sax");
        InputStream inputStream = Runner.class.getClassLoader().getResourceAsStream("knight.xml");
        Knight knight = parser.knightParser(inputStream);

        Action.sortAmmunition(knight.getAmmunitionList(), CUSTOM_SORT_CRITERION);
        Report.report(knight);
        Report.report(Action.searchPrice(knight.getAmmunitionList(), CUSTOM_MIN_PRICE, CUSTOM_MAX_PRICE));

       /* Parser parser2 = knightParserFactory.createKnightParser("stax");
        Knight knight1 = parser2.knightParser(inputStream);
        System.out.println(knight1.toString());*/
    }
}
