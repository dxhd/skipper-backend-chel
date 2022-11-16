package com.tinkoff.skipper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkipperErrorResponse {
    private Integer status;
    private String message;
}
