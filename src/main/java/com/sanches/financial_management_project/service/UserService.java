package com.sanches.financial_management_project.service;

import com.sanches.financial_management_project.dto.request.UserRequestDTO;
import com.sanches.financial_management_project.dto.response.UserResponseDTO;
import com.sanches.financial_management_project.exceptions.ResourceNotFoundException;
import com.sanches.financial_management_project.mapper.UserMapper;
import com.sanches.financial_management_project.repository.UserRepository;
import com.sanches.financial_management_project.utils.UsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public List<UserResponseDTO> listUsers() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }

    public UserResponseDTO findUserById(Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        return userMapper.toResponseDTO(entity);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return userMapper.toResponseDTO(userRepository.save(userMapper.toEntity(userRequestDTO)));
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        var entity = userRepository.findById(userRequestDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        if (!UsernameValidator.isValidUsername(userRequestDTO.getUsername())) {
            throw new IllegalArgumentException("Invalid Username!");
        }

        entity.setUsername(userRequestDTO.getUsername());
        entity.setFirstName(userRequestDTO.getFirstName());
        entity.setLastName(userRequestDTO.getLastName());
        entity.setEmail(userRequestDTO.getEmail());

        if (!userRequestDTO.getPassword().isEmpty() && !passwordEncoder.matches(userRequestDTO.getPassword(), entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }

        userRepository.save(entity);

        return userMapper.toResponseDTO(entity);
    }

    public void deleteUser (Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        userRepository.deleteById(entity.getId());
    }
}
