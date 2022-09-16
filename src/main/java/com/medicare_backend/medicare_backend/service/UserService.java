package com.medicare_backend.medicare_backend.service;

import com.medicare_backend.medicare_backend.entity.User;
import com.medicare_backend.medicare_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

}
