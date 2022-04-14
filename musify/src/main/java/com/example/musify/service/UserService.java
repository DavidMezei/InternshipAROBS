package com.example.musify.service;

import com.example.musify.UserUtils;
import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserLoginDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.exception.ResourceNotFoundException;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.UserMapper;
import com.example.musify.mapper.UserMapperImpl;
import com.example.musify.model.User;
import com.example.musify.repo.UserRepository;
import com.example.musify.security.JWTBlacklist;
import com.example.musify.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public List<UserViewDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toViewDTOList(users);
    }

    @Transactional
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

    @Transactional
    public String loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findUserByEmail(userLoginDTO.getEmail());
        String encryptedPassword = encryptPassword(userLoginDTO.getPassword());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        if(!UserUtils.isActive(user.get())) {
            throw new IllegalArgumentException("User is not active");
        }

        return JwtUtils.generateToken(user.get().getId(), user.get().getEmail(), user.get().getRole());
    }

    @Transactional
    public UserViewDTO updateUserStatus(Integer id, String operation) {
        String newStatus = "";

        if (!UserUtils.isCurrentAdmin()) {
            throw new IllegalArgumentException("Only admin can change user status");
        }

        if (operation.equals("ACTIVATE")) {
            newStatus = "active";
        } else if (operation.equals("DEACTIVATE")) {
            newStatus = "inactive";
        }

        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        User user = optional.get();
        user.setStatus(newStatus);
        userRepository.save(user);

        return userMapper.toViewDTO(user);
    }

    @Transactional
    public UserViewDTO updateUser(Integer id, UserDTO userDTO) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        if (!UserUtils.isCurrentAdmin() && !UserUtils.isOperationOnSelf(id)) {
            throw new UnauthorizedException("Users can only update their own info");
        }

        User user = optional.get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        String encryptedPassword = encryptPassword(userDTO.getPassword());
        user.setEncryptedPassword(encryptedPassword);
        user.setCountry(userDTO.getCountry());

        user = userRepository.save(user);

        return userMapper.toViewDTO(user);
    }

    @Transactional
    public UserViewDTO updateUserRole(Integer id, String operation) {
        String newRole = "";

        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can modify user roles");
        }

        if (operation.equals("PROMOTE")) {
            newRole = "admin";
        } else if (operation.equals("DEMOTE")) {
            newRole = "user";
        }

        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no user with id = " + id);
        }

        User user = optional.get();
        user.setRole(newRole);
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
