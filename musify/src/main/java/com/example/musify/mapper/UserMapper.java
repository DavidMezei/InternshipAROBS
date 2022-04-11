package com.example.musify.mapper;

import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserViewDTO;
import com.example.musify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "fullName", expression = "java(user.getFullName())")
    UserViewDTO toViewDTO(User user);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    List<UserViewDTO> toViewDTOList(List<User> users);

    List<UserDTO> toDTOList(List<User> users);
}
