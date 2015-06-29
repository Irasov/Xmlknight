package com.epam.irasov.xmlknight.exception;

public class ParseException extends RuntimeException {
    public ParseException(Throwable cause){
        super(cause);
    }

    public ParseException(String message,Throwable cause){
        super(message,cause);
    }

    public ParseException(String message){
        super(message);
    }
    public ParseException(){

    }
}
