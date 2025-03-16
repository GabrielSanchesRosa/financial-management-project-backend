package com.sanches.financial_management_project.controller;

import com.sanches.financial_management_project.dto.request.UserRequestDTO;
import com.sanches.financial_management_project.dto.response.UserResponseDTO;
import com.sanches.financial_management_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> listUsers() {
        return userService.listUsers();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDTO));
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userRequestDTO));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
