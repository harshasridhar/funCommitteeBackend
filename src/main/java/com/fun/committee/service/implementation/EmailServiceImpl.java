package com.fun.committee.service.implementation;

import com.fun.committee.ConfigKeyValues;
import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.service.interfaces.ConfigKeyValuesService;
import com.fun.committee.service.interfaces.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by harshams on 27/06/2020
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public ConfigKeyValuesService configKeyValuesService;

    private static final String EMAIL_TEMPLATE_USERNAME = "{{Username}}";
    private static final String EMAIL_TEMPLATE_PASSWORD = "{{Password}}";
    private static final String EMAIL_TEMPLATE_NAME = "{{Name}}";
    private static final String EMAIL_TEMPLATE_IP_ADDRESS = "{{IP_ADDRESS}}";

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);


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
            logger.info("Email successfully sent to : "+to);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Async
    public void sendCredentialsEmail()throws Exception{
        Scanner scanner = new Scanner(new ClassPathResource("emailIdPasswd.csv").getInputStream());
        scanner.useDelimiter("\n");
        while(scanner.hasNext()){
            String line = scanner.next();
            String cols[] =line.split(",");
            if(cols[0].equalsIgnoreCase("hmsridhar@gigsky.com")){
                String content;
                content = StreamUtils.copyToString(
                        new ClassPathResource("credentials.html").getInputStream(), Charset.defaultCharset());
                content = content.replace(EMAIL_TEMPLATE_NAME,cols[1])
                        .replace(EMAIL_TEMPLATE_USERNAME,cols[0])
                        .replace(EMAIL_TEMPLATE_PASSWORD,cols[2])
                        .replace(EMAIL_TEMPLATE_IP_ADDRESS, InetAddress.getLocalHost().getHostAddress());
                sendEmail(cols[0],"Portal Credentials",content);
            }
        }

    }
}
