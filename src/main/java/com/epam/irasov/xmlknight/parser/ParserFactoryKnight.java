package com.epam.irasov.xmlknight.parser;

public class ParserFactoryKnight {
    public enum TypeParser {
        SAX, STAX, DOM
    }

    public Parsers createKnightParser(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new SAXParserKnight();
            case STAX:
                return new StAXParseKnightr();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
