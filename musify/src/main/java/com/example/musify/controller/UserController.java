package com.example.musify.controller;

import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserLoginDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.model.User;
import com.example.musify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(@RequestParam Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable int id) {
        UserViewDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserViewDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserViewDTO userViewDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserViewDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        UserViewDTO userViewDTO = userService.loginUser(userLoginDTO);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserViewDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        UserViewDTO userViewDTO = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }
}
