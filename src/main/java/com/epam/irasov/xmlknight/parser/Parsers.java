package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.Knight;

import java.io.InputStream;

public interface Parsers {

   Knight knightParser(InputStream inputStream);
}
