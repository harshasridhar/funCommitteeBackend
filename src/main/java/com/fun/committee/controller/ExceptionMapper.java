package com.fun.committee.controller;

import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.model.json.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by harshams on 24/06/2020
 */
@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FunCommitteeException.class)
    public final ResponseEntity<Object> handleFunCommitteeExceptions(FunCommitteeException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(ex.getErrorCode());
        errorMessage.setErrorId(ex.getErrorCode().getLongValue());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
