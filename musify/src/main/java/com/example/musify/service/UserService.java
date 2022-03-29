package com.example.musify.service;

import com.example.musify.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getMessage(){
        return "Hello String from Africa!";
    }
}
