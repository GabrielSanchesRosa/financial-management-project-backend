package com.sanches.financial_management_project.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
