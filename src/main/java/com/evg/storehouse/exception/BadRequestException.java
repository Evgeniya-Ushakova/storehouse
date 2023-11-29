package com.evg.storehouse.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class BadRequestException extends RuntimeException implements StorehouseException {

    private int code;
    private String message;

    public BadRequestException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
