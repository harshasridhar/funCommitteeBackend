package com.fun.committee;

/**
 * Created by harshams on 25/06/2020
 */
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(2000),
    INVALID_ARGUMENTS(2001);

    private long errorCode;

    ErrorCode(int errorCode){
        this.errorCode = errorCode;
    }

    public long getLongValue() {
        return errorCode;
    }}
