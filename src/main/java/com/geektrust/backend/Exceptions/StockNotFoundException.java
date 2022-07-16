package com.geektrust.backend.Exceptions;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException() {}

    public StockNotFoundException(String message) {
        super(message);
    }
}
