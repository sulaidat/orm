package org.jminiorm.exception;

@SuppressWarnings("serial")
public class DBException extends RuntimeException {

    public DBException(Throwable t) {
        super(t);
    }

    public DBException(String string) {
        this(new Exception(string));
    }

}
