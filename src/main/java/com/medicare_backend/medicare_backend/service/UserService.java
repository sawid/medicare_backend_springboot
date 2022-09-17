package com.medicare_backend.medicare_backend.service;

import com.medicare_backend.medicare_backend.entity.Authentication;
import com.medicare_backend.medicare_backend.entity.User;
import com.medicare_backend.medicare_backend.repository.UserRepository;
import com.medicare_backend.medicare_backend.service.AuthenticationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private AuthenticationService authservice;

    public String registerUser(User user) {
        String returnString = "";
        try {
            List<User> userIsMatch = userRepository.findByName(user.getName());
            if (userIsMatch != null && userIsMatch.isEmpty()) {
                System.out.println(userIsMatch);
                byte[] hash = authservice.getEncryptedPassword(user.getPasswordId(), "salt".getBytes());
                user.setPasswordId(authservice.bytesToHex(hash));
                userRepository.save(user);
                returnString = "Register Success";
            } else {
                returnString = "Already User";
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return returnString;
    }

    public String loginUser(Authentication auth) {
        try {
            byte[] passwordToByte = authservice.hexToByte("C81BDC96AFBB8AD447C176B2CD1DBFE1568C11EE8B6205DE29B27F336B3AE2DE8B122880A4FFB1C3A85FE9F391EE762DD0A990A73F10DAF44EC693A33EE8B7C3");
            System.out.println(authservice.authenticate("FolkSawit@gmail.com", passwordToByte, "salt".getBytes()));
            System.out.println(authservice.authenticate("FolkSawit@gmail.com", passwordToByte, "salty".getBytes()));
        return "token";
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "token";
    }

    public List<User> getListUser() {
        List<User> user = new ArrayList<User>();
        try {
            user = userRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

}
