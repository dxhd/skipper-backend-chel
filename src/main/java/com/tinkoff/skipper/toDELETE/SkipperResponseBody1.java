package com.tinkoff.skipper.toDELETE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkipperResponseBody1<T> {
  private Integer status;
  private T data;
}
