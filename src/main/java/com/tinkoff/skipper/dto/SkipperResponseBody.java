package com.tinkoff.skipper.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkipperResponseBody<T> {
  private Integer status;
  private T data;
}
