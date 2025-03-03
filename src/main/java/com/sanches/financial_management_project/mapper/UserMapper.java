package com.sanches.financial_management_project.mapper;

import com.sanches.financial_management_project.dto.UserDTO;
import com.sanches.financial_management_project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    List<UserDTO> toDtoList(List<User> users);

    User toEntity(UserDTO userDTO);
    List<User> toEntityList(List<UserDTO> usersDTO);
}
