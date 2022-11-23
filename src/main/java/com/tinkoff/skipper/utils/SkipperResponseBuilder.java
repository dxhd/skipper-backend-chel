package com.tinkoff.skipper.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public final class SkipperResponseBuilder {

    public static <T> ResponseEntity<T> buildResponse(HttpStatus status, T data) {
        return ResponseEntity.status(status).body(data);
    }
}
