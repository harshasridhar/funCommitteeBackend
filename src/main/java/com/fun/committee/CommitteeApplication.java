package com.fun.committee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://127.0.0.1")
public class CommitteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommitteeApplication.class, args);
    }

}
