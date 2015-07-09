package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.Knight;
import com.epam.irasov.xmlknight.exception.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class JAXBParser implements Parser {
    @Override
    public Knight knightParser(InputStream inputStream) {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(Knight.class);
            Unmarshaller u = jc.createUnmarshaller();
            return (Knight) u.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new ParseException(e);
        }
    }
}
