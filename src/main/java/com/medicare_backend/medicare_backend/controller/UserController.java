package com.medicare_backend.medicare_backend.controller;

import com.medicare_backend.medicare_backend.service.UserService;
import com.medicare_backend.medicare_backend.entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/users")
    public void newUser(@RequestBody User user) {
        userService.registerUser(user);
    }

}

