package com.epam.irasov.xmlknight.parser;

import com.epam.irasov.xmlknight.entity.Knight;

import java.io.InputStream;

public abstract class AbstractParser {
  /*  protected Knight knight;

    public AbstractParser(){
        knight=null;
    }

    public AbstractParser(Knight knight){
        this.knight = knight;
    }

    public Knight getKnight(){
        return knight;
    }*/

    abstract public Knight knightParser(InputStream inputStream);
}
