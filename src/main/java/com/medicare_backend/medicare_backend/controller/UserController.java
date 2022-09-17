package com.medicare_backend.medicare_backend.controller;

import com.medicare_backend.medicare_backend.service.UserService;
import com.medicare_backend.medicare_backend.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getListUser() {
        return userService.getListUser();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserByOneUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public String registerNewUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

}

