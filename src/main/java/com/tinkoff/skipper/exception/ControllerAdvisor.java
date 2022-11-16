package com.tinkoff.skipper.exception;

import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.tinkoff.skipper.exception.ErrorResponse;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
      ErrorResponse res = new ErrorResponse(status.value(), message);
      return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(SkipperNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSkipperNotFoundException(Exception e) {
      return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

}
