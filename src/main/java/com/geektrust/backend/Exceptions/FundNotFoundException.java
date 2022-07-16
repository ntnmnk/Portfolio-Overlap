package com.geektrust.backend.Exceptions;

public class FundNotFoundException extends RuntimeException {

    public FundNotFoundException() {}

    public FundNotFoundException(String message) {
        super(message);
    }
}
