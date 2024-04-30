package com.example.demo.FormatRespon;

import lombok.Data;

@Data
public class ResponObject {
    private String status;
    private String message;
    private Object data;

    public ResponObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
