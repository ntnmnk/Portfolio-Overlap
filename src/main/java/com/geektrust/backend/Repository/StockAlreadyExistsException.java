package com.geektrust.backend.Repository;

public class StockAlreadyExistsException extends RuntimeException{
    public StockAlreadyExistsException(){}


    public StockAlreadyExistsException(String message) {
        super(message);
    }
}


