package com.spotify.oauth2.api;

public enum StatusCode {
    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_401(401, "Invalid access token");
    private final int code; //without Private also do the validation but not required getter and setter methods
    private final String msg; //without Private also do the validation but not required getter and setter methods

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String setMsg() {
        return msg;
    }
}
