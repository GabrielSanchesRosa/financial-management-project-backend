package com.sanches.financial_management_project.service;

import com.sanches.financial_management_project.dto.UserDTO;
import com.sanches.financial_management_project.exceptions.ResourceNotFoundException;
import com.sanches.financial_management_project.mapper.UserMapper;
import com.sanches.financial_management_project.model.User;
import com.sanches.financial_management_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public List<UserDTO> listUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDTO findUserById(Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        return userMapper.toDto(entity);
    }

    public UserDTO createUser(User user) {
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDTO updateUser(UserDTO user) {
        var entity = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        entity.setUserName(user.getUserName());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        userRepository.save(entity);

        return userMapper.toDto(entity);
    }

    public void deleteUser (Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No users found for this ID!"));

        userRepository.deleteById(entity.getId());
    }
}
