package com.fun.committee.controller;

import com.fun.committee.model.json.ResponseMessage;
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

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setType("error");
        responseMessage.setMessage("INTERNAL SERVER ERROR!");
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
