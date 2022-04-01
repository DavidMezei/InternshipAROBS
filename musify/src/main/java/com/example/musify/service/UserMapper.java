package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "fullName", expression = "java(user.getFullName())")
    UserViewDTO toViewDTO(User user);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
