package com.tinkoff.skipper.exception;

import com.tinkoff.skipper.dto.SkipperErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private ResponseEntity<SkipperErrorResponse> buildErrorResponse(HttpStatus status, String message) {
      SkipperErrorResponse response = new SkipperErrorResponse(status.value(), message);
      return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(SkipperNotFoundException.class)
    public ResponseEntity<SkipperErrorResponse> handleSkipperNotFoundException(Exception e) {
      return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<SkipperErrorResponse> handleInternalError(Exception e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(SkipperBadRequestException.class)
    public ResponseEntity<SkipperErrorResponse> handleBadRequestException(Exception e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }


}
