package com.sanches.financial_management_project.controller;

import com.sanches.financial_management_project.dto.request.UserRequestDTO;
import com.sanches.financial_management_project.dto.response.UserResponseDTO;
import com.sanches.financial_management_project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO userRequestDTO) {
        return authenticationService.login(userRequestDTO);
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO userRequestDTO) {
        return authenticationService.register(userRequestDTO);
    }
}
