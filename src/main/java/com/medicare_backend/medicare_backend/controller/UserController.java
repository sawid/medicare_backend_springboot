package com.medicare_backend.medicare_backend.controller;

import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.repository.UserRepository;
import com.medicare_backend.medicare_backend.schema.entity.Authentication;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.entity.User;
import com.medicare_backend.medicare_backend.service.AuthenticationService;
import com.medicare_backend.medicare_backend.service.TokenAuthenticationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserController {
    
    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    private AuthenticationService authservice;

    private TokenAuthenticationService tokenService;

    // Method User

    public String registerUser(Patient user) {
        String returnString = "";
        try {
            List<Patient> userIsMatch = patientRepository.findByPatientNationalId(user.getPatientNationalId());
            System.out.println(userIsMatch);
            if (userIsMatch != null && userIsMatch.isEmpty()) {
                System.out.println(userIsMatch);
                byte[] hash = authservice.getEncryptedPassword(user.getPatientPassword(), "salt".getBytes());
                user.setPatientPassword(authservice.bytesToHex(hash));
                patientRepository.save(user);
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
        String returnString = "";
        try {
            List<Patient> userQuery = patientRepository.findByPatientNationalId(auth.getUsername());
            if (!(userQuery != null && userQuery.isEmpty())) {
                String userPassword = userQuery.get(0).getPatientPassword();
                byte[] passwordToByte = authservice.hexToByte(userPassword);
                if(authservice.authenticate(auth.getPassword(), passwordToByte, "salt".getBytes())) {
                    System.out.println(userQuery.get(0).getpatientHNId());
                    String authToken = tokenService.generateJWTToken(userQuery.get(0).getpatientHNId());
                    // String decodedjwt = tokenService.verifyJWTToken(authToken);
                    // System.out.println(decodedjwt);
                    returnString = authToken;
                    return returnString;
                }
                else {
                    returnString = "Auth Failed";
                }
            }
            else {
                returnString = "User Not Found";
            }
            
            return returnString;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return "Error";
    }

    // public List<User> getListUser() {
    //     List<User> user = new ArrayList<User>();
    //     try {
    //         user = userRepository.findAll();
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    //     return user;
    // }

    // public Optional<User> getUserById(Long userId) {
    //     return userRepository.findById(userId);
    // }

    // public String addTaskData(String authtoken) {
    //     String atoken = authservice.verifyJWTToken(authtoken);
    //     return atoken;
    // }

    // Method Function
}
