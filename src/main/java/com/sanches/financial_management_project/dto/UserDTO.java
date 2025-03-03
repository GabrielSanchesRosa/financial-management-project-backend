package com.sanches.financial_management_project.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
}
