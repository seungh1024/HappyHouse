package com.ssafy.happyhouse.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private int code = HttpStatus.OK.value();
    private Object result;

    public ApiResponse() {}

    public ApiResponse(int code, Object result) {
        this.code = code;
        this.result = result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
