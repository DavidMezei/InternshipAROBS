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
import java.util.List;
import java.util.Optional;

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

    public List<UserViewDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toViewDTOList(users);
    }

    public UserViewDTO getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return userMapper.toViewDTO(user.get());
    }

    public UserViewDTO createUser(UserDTO userDTO) {
        Optional<User> optional = userRepository.findUserByEmail(userDTO.getEmail());

        if (optional.isPresent()) {
            throw new IllegalArgumentException("Email " + userDTO.getEmail() + " is already registered");
        }

        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = encryptPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setRole("user");
        user.setStatus("active");
        user = userRepository.save(user);

        return userMapper.toViewDTO(user);
    }

    public String loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findUserByEmail(userLoginDTO.getEmail());
        String encryptedPassword = encryptPassword(userLoginDTO.getPassword());

        if (user.isPresent() && user.get().getEncryptedPassword().equals(encryptedPassword)) {

            return JwtUtils.generateToken(user.get().getId(), user.get().getEmail(), user.get().getRole());
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    public UserViewDTO updateUser(int id, UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encryptedPassword = encryptPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setId(id);
        userRepository.save(user);

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
