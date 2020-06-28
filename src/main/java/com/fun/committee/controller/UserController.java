package com.fun.committee.controller;

import com.fun.committee.model.Role;
import com.fun.committee.model.json.ResponseMessage;
import com.fun.committee.model.json.User;
import com.fun.committee.model.json.UserList;
import com.fun.committee.service.implementation.UserDetailsServiceImpl;
import com.fun.committee.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by harshams on 24/06/2020
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseMessage registerUser(@RequestBody User user)throws Exception{
        userService.addUser(user);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("User added successfully!");
        return responseMessage;
    }

    @GetMapping("/users")
    public UserList getUsers(Principal principal){
        return userService.getUsers(principal.getName());
    }
}
