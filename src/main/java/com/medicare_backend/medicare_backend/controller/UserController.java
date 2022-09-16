package com.medicare_backend.medicare_backend.controller;

import com.medicare_backend.medicare_backend.service.UserService;
import com.medicare_backend.medicare_backend.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public void save() {
        User user = new User();
        user.setFirstName("Sawit");
        user.setLastName("Limkiatsataporn");
        user.setEmailId("zasawidza@gmail.com");
        userService.registerUser(user);
    }
}

