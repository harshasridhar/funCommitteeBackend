package com.fun.committee.controller;

import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.model.json.Question;
import com.fun.committee.model.json.ResponseMessage;
import com.fun.committee.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by harshams on 25/06/2020
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("")
    public ResponseMessage addQuestion(@RequestBody Question question)throws Exception{
        ResponseMessage responseMessage = new ResponseMessage();
        questionService.addQuestion(question);
        responseMessage.setMessage("Success");
//        if(true)
//            throw new FunCommitteeException(ErrorCode.INTERNAL_SERVER_ERROR,"My error");
        return responseMessage;
    }
}
