package com.tinkoff.skipper.exception;

public class SkipperBadRequestException extends RuntimeException{
    public SkipperBadRequestException(String message) {
        super(message);
    }
}
