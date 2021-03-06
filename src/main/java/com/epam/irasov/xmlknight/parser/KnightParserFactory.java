package com.epam.irasov.xmlknight.parser;

public class KnightParserFactory {
    public enum TypeParser {
        SAX, STAX, DOM, JAXB
    }

    public Parser createKnightParser(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new SAXKnightParser();
            case STAX:
                return new StAXKnightParser();
            case DOM:
                return new DOMKnightParser();
            case JAXB:
                return new JAXBParser();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
