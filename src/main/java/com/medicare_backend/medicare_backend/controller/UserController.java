package com.medicare_backend.medicare_backend.controller;


import com.medicare_backend.medicare_backend.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.medicare_backend.medicare_backend.entity.Authentication;
import com.medicare_backend.medicare_backend.entity.User;
import com.medicare_backend.medicare_backend.request.AddTask;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    


    @GetMapping("/users")
    public ResponseEntity<?> getListUser() {
        List<User> data = userService.getListUser();
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("User List Not Found");
        }
    }

    @GetMapping("/users/findUserById/{id}")
    public Optional<User> getUserByOneUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        String data = userService.registerUser(user);
        if (data == "Register Success") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
        
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> loginToUser(@RequestBody Authentication auth) {
        String data = userService.loginUser(auth);
        if (data != "Auth Failed") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
        
    }

    @PostMapping("/authtication/addtask")
    public String userAddTask(@RequestHeader("authtoken") String authtoken, @RequestBody AddTask task) {
        try {
            String dataLogin = userService.addTaskData(authtoken);
            return dataLogin;
        } catch (InvalidParameterException e) {
            return "Token InValid";
        }
        
    }


}

