package com.epam.irasov.xmlknight.parser;

public class KnightParserFactory {
    public enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractParser createKnightParser(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new KnightParserSAX();
            case STAX:
                return new KnightParserStAX();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
