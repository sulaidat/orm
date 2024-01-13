package org.proorm.exception;

public class UnexpectedNumberOfItemsException extends Exception {

    private int actual;

    public UnexpectedNumberOfItemsException(int actual) {
        this.actual = actual;
    }

    public int getActual() {
        return actual;
    }
}
