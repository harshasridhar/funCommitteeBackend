package com.fun.committee.controller;

import com.fun.committee.ConfigKeyValues;
import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.model.json.ResponseMessage;
import com.fun.committee.service.interfaces.ConfigKeyValuesService;
import com.fun.committee.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by harshams on 27/06/2020
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ConfigKeyValuesService configKeyValuesService;

    @PostMapping("/credentials")
    public ResponseMessage sendCredentials()throws Exception{
        if(!configKeyValuesService.getBooleanConfigKeyValue(ConfigKeyValues.EMAIL_NOTIFICATION_ENABLED,ConfigKeyValues.DefaultValue.EMAIL_NOTIFICATION_ENABLED)){
            throw new FunCommitteeException(ErrorCode.NOTIFICATIONS_DISABLED,"Notifications are disabled");
        }
//        emailService.sendEmail("hmsridhar@gigsky.com","Credentials","Your credentials are blah blah blah");
        emailService.sendCredentialsEmail();
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("Emails Scheduled Sucessfully!");
        return responseMessage;
    }
}
