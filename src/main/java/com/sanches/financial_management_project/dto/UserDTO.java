package com.sanches.financial_management_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
