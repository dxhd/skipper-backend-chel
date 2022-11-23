package com.tinkoff.skipper.exception;

import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.message.AuthException;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SkipperNotFoundException.class)
    public ResponseEntity<String> handleSkipperNotFoundException(Exception e) {
      return SkipperResponseBuilder.buildResponse(
              HttpStatus.NOT_FOUND,
              e.getMessage()
      );
    }

    // fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalError(Exception e) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage());
    }

    @ExceptionHandler(SkipperBadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuthException(Exception e) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.UNAUTHORIZED,
                e.getMessage()
        );
    }
}
