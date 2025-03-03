package com.sanches.financial_management_project.controller;

import com.sanches.financial_management_project.dto.UserDTO;
import com.sanches.financial_management_project.model.User;
import com.sanches.financial_management_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }

    @GetMapping(value = "{id}")
    public UserDTO findUserById(@PathVariable(value = "id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
