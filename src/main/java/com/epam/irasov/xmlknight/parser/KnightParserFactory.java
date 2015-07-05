package com.epam.irasov.xmlknight.parser;

public class KnightParserFactory {
    public enum TypeParser {
        SAX, STAX, DOM
    }

    public Parser createKnightParser(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new SAXKnightParser();
            case STAX:
                return new StAXKnightParser();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
