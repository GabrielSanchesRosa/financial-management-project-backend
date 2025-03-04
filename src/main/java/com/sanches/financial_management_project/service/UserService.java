package com.sanches.financial_management_project.service;

import com.sanches.financial_management_project.dto.UserDTO;
import com.sanches.financial_management_project.exceptions.ResourceNotFoundException;
import com.sanches.financial_management_project.mapper.UserMapper;
import com.sanches.financial_management_project.model.User;
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

    public List<UserDTO> listUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDTO findUserById(Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        return userMapper.toDto(entity);
    }

    public UserDTO createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDTO updateUser(UserDTO user) {
        var entity = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        if (!UsernameValidator.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Invalid Username!");
        }

        entity.setUsername(user.getUsername());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());

        if (!user.getPassword().isEmpty() && !user.getPassword().equals(entity.getPassword())) {
            entity.setPassword(user.getPassword());
        }

        userRepository.save(entity);

        return userMapper.toDto(entity);
    }

    public void deleteUser (Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        userRepository.deleteById(entity.getId());
    }
}
