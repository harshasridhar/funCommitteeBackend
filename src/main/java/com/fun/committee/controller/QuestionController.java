package com.fun.committee.controller;

import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.model.json.*;
import com.fun.committee.service.QuestionService;
import com.fun.committee.service.interfaces.HasAnsweredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by harshams on 25/06/2020
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    HasAnsweredService hasAnsweredService;

    @PostMapping("")
    public ResponseMessage addQuestion(@RequestBody Question question)throws Exception{
        ResponseMessage responseMessage = new ResponseMessage();
        questionService.addQuestion(question);
        responseMessage.setMessage("Question added successfully");
//        if(true)
//            throw new FunCommitteeException(ErrorCode.INTERNAL_SERVER_ERROR,"My error");
        return responseMessage;
    }

    @GetMapping("")
    public QuestionList getQuestions()throws Exception{
        return questionService.getQuestions();
    }

    @PostMapping("/answer")
    public ResponseMessage submitQuestionaire(@RequestBody Answers answers)throws Exception{
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("Questionaire submitted successfully");
        hasAnsweredService.submitQuestionaire(answers);
        return responseMessage;
    }

    @GetMapping("/answer")
    public AnswersList getAnswers(@RequestParam String username, Principal principal)throws Exception{
        if(!username.equalsIgnoreCase(principal.getName())){
            throw new FunCommitteeException(ErrorCode.INVALID_DATA_ACCESS,"Invalid Data Access, this incident will be reported");
        }
        return hasAnsweredService.getAnswers(username);
    }
}
