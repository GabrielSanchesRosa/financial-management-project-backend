package com.sanches.financial_management_project.mapper;

import com.sanches.financial_management_project.dto.request.UserRequestDTO;
import com.sanches.financial_management_project.dto.response.UserResponseDTO;
import com.sanches.financial_management_project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO toResponseDTO(User user);
    List<UserResponseDTO> toResponseDTOList(List<User> users);
}
