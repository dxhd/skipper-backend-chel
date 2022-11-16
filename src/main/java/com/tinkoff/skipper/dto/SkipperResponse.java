package com.tinkoff.skipper.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkipperResponse {
  private Integer status;
  private Object data;

  public static ResponseEntity<SkipperResponse> buildResponse(HttpStatus status, Object data) {
    SkipperResponse response = new SkipperResponse(status.value(), data);
    return ResponseEntity.status(status).body(response); 
  }
}
