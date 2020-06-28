package com.fun.committee.controller;

import com.fun.committee.model.json.GameStatus;
import com.fun.committee.service.interfaces.GameCompletionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by harshams on 28/06/2020
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameCompletionStatusService gameCompletionStatusService;

    @GetMapping("/status")
    public GameStatus getGameStatus(Principal principal){
        String username = principal.getName();
        return gameCompletionStatusService.getGameStatusForUser(username);
    }
}
