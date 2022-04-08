package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserLoginDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.mapper.UserMapper;
import com.example.musify.mapper.UserMapperImpl;
import com.example.musify.model.User;
import com.example.musify.repo.UserRepository;
import com.example.musify.security.JWTBlacklist;
import com.example.musify.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JWTBlacklist blacklist;

    @Autowired
    public UserService(UserRepository userRepository, JWTBlacklist blacklist) {
        this.userRepository = userRepository;
        this.blacklist = blacklist;
        this.userMapper = new UserMapperImpl();
    }

    public UserViewDTO getUserById(Integer id) {
        User user = userRepository.getUserById(id);
        return userMapper.toViewDTO(user);
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

    public String loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.getUserByEmail(userLoginDTO.getEmail());
        String encryptedPassword = encryptPassword(userLoginDTO.getPassword());

        if (user != null && user.getEncryptedPassword().equals(encryptedPassword)) {
            return JwtUtils.generateToken(user.getId(), user.getEmail(), user.getRole());
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

    public String logoutUser(String header) {
        blacklist.addToken(JwtUtils.extractToken(header));
        return "Successfully logged out";
    }
}
