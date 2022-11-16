package com.tinkoff.skipper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(SkipperNotFoundException.class)
    public ResponseEntity<ErrorResponse> handeSkipperNotFoundException(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
    }
}
