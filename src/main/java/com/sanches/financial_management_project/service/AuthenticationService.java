package com.sanches.financial_management_project.service;

import com.sanches.financial_management_project.dto.request.UserRequestDTO;
import com.sanches.financial_management_project.dto.response.UserResponseDTO;
import com.sanches.financial_management_project.mapper.UserMapper;
import com.sanches.financial_management_project.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationService {

    @Autowired
    private JWTService JWTService;

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    UserMapper userMapper = UserMapper.INSTANCE;

    public String login(UserRequestDTO userRequestDTO) throws ResponseStatusException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword()));

        return authentication.isAuthenticated() ? JWTService.generateToken(userRequestDTO.getUsername()) : "Incorrect Username or Password!";
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        if (authenticationRepository.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            System.out.println("Username already exists!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        if (authenticationRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            System.out.println("Email already exists!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        var entity = authenticationRepository.save(userMapper.toEntity(userRequestDTO));

        return userMapper.toResponseDTO(entity);
    }
}
