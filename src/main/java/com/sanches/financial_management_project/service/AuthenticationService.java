package com.sanches.financial_management_project.service;

import com.sanches.financial_management_project.dto.UserDTO;
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

    public String login(UserDTO userDTO) throws ResponseStatusException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        return authentication.isAuthenticated() ? JWTService.generateToken(userDTO.getUsername()) : "Incorrect Username or Password!";
    }

    public UserDTO register(UserDTO userDTO) {
        if (authenticationRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            System.out.println("Username already exists!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        if (authenticationRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            System.out.println("Email already exists!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        var entity = authenticationRepository.save(userMapper.toEntity(userDTO));

        return userMapper.toDto(entity);
    }
}
