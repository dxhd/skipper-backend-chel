package com.tinkoff.skipper.exception;

public class SkipperNotFoundException extends RuntimeException {
    public SkipperNotFoundException(String message) {
        super(message);
    }
}
