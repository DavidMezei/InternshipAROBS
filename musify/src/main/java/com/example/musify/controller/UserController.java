package com.example.musify.controller;

import com.example.musify.model.User;
import com.example.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String helloSpring(@RequestParam String id){
        System.out.println("id: "+id);
        return userService.getMessage();
    }

    @PostMapping
    public String post(@RequestBody User user){
        System.out.println("post:");
        return userService.getMessage();
    }

}
