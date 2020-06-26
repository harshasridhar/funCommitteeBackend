package com.fun.committee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 25/06/2020
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FunCommitteeException extends Exception {

    private ErrorCode errorCode;

    public FunCommitteeException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

}
