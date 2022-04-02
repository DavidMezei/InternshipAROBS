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

    @GetMapping("/{id}")
    public ResponseEntity<UserViewDTO> getUserById(@PathVariable Integer id) {
        UserViewDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserViewDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserViewDTO userViewDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String token = userService.loginUser(userLoginDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserViewDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        UserViewDTO userViewDTO = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(userViewDTO, HttpStatus.OK);
    }
}
