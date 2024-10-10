package com.example.demo.exception;

import lombok.Data;

public enum ErrorCode {
    USER_EXISTS(401,"User exitsted"),
    INVALID_PASSWORD(409,"Invalid password or not verified"),
    DATA_EXISTS(409,"Email already exists or Number phone already exists"),
    NOT_FOUND_CUSTOMER(409,"Not found any member"),
    INVALID_PHONE(409,"PhoneNumber is invalid"),
    NOT_FOUND_TOPIC(409,"Not found topic"),
    NOT_FOUND_PART(409,"Not found part"),
    NOT_NULL(409,"Not null"),
    INVALID_EMAIL(409,"Email is invalid"),
    INVALID_TYPE(409,"Invalid Type");
    private int code;
    private String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
