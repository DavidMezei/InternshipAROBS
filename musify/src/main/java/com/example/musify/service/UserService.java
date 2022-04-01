package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserLoginDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.model.User;
import com.example.musify.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapperImpl();
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public UserViewDTO getUserById(int id) {
        User user = userRepository.getUserById(id);
        return userMapper.toViewDTO(user);
    }

    public UserViewDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = encryptPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setRole("user");
        user.setStatus("active");
        int id = userRepository.createUser(user);
        user.setId(id);

        return userMapper.toViewDTO(user);
    }

    public UserViewDTO loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.getUserByEmail(userLoginDTO.getEmail());
        String encryptedPassword = encryptPassword(userLoginDTO.getPassword());

        if (user != null && user.getEncryptedPassword().equals(encryptedPassword)) {
            return userMapper.toViewDTO(user);
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    public UserViewDTO updateUser(int id, UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = encryptPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setId(id);
        userRepository.updateUser(user);

        return userMapper.toViewDTO(user);
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedPassword = md.digest(password.getBytes());
            BigInteger bigInteger = new BigInteger(1, hashedPassword);
            return bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
