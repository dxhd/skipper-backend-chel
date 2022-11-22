package com.tinkoff.skipper.utils;

import com.tinkoff.skipper.dto.SkipperResponseBody;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public final class SkipperResponseBuilder {

    public static <T> ResponseEntity<SkipperResponseBody<?>> buildResponse(HttpStatus status, T data) {
        SkipperResponseBody<T> response = new SkipperResponseBody<>(status.value(), data);
        return ResponseEntity.status(status).body(response);
    }


    //TODO: дописать мапу для передачи сообщений методу buildResponse
    public final static Map<SkipperResponseMessage, String> RESPONSE_MESSAGE = new HashMap<>();
    public enum SkipperResponseMessage {

    }

}
