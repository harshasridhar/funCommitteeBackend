package com.fun.committee.service.implementation;

import com.fun.committee.ConfigKeyValues;
import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.service.interfaces.ConfigKeyValuesService;
import com.fun.committee.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

/**
 * Created by harshams on 27/06/2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public ConfigKeyValuesService configKeyValuesService;

    /*
    spring.mail.host = smtp.gmail.com
spring.mail.username = someoneacc2017@gmail.com
spring.mail.password = someoneacc2017@
     */
    @PostConstruct
    public void init(){
        JavaMailSenderImpl ms = (JavaMailSenderImpl) emailSender;
        ms.setHost("smtp.gmail.com");
        ms.setUsername(configKeyValuesService.getStringConfigKeyValue(ConfigKeyValues.EMAIL_ID,ConfigKeyValues.DefaultValue.EMAIL_ID));
        ms.setPassword(configKeyValuesService.getStringConfigKeyValue(ConfigKeyValues.EMAIL_PASSWORD,ConfigKeyValues.DefaultValue.EMAIL_PASSWORD));
    }

    @Async
    public void sendEmail(String to,String subject, String messageText){
        try {
            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject(subject);
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(to);
            helper.setText(messageText,true);

            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
