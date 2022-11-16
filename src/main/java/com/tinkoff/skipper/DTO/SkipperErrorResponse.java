package com.tinkoff.skipper.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkipperErrorResponse {
    private Integer status;
    private String message;
}
