package com.medicare_backend.medicare_backend.controller;

import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.repository.UserRepository;
import com.medicare_backend.medicare_backend.schema.entity.Authentication;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.entity.User;
import com.medicare_backend.medicare_backend.service.AuthenticationService;
import com.medicare_backend.medicare_backend.service.InternalPayload;
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

    public InternalPayload registerUser(Patient user) {
        InternalPayload returnPayload = new InternalPayload("0", "Okay");
        
        try {
            List<Patient> userIsMatch = patientRepository.findByPatientNationalId(user.getPatientNationalId());
            System.out.println(userIsMatch);
            if (userIsMatch != null && userIsMatch.isEmpty()) {
                System.out.println(userIsMatch);
                byte[] hash = authservice.getEncryptedPassword(user.getPatientPassword(), "salt".getBytes());
                user.setPatientPassword(authservice.bytesToHex(hash));
                patientRepository.save(user);
                returnPayload.setStatusCode("0");
                returnPayload.setStatusText("Register Success");
                return returnPayload;
            } else {
                returnPayload.setStatusCode("1");
                returnPayload.setStatusText("Already User");
                return returnPayload;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            returnPayload.setStatusCode("1");
            returnPayload.setStatusText("Error On System");
            return returnPayload;
        }
        
    }

    public InternalPayload loginUser(Authentication auth) {
        String returnString = "";
        try {
            
            List<Patient> userQuery = patientRepository.findByPatientNationalId(auth.getUsername());
            if (!(userQuery != null && userQuery.isEmpty())) {
                String userPassword = userQuery.get(0).getPatientPassword();
                byte[] passwordToByte = authservice.hexToByte(userPassword);
                if(authservice.authenticate(auth.getPassword(), passwordToByte, "salt".getBytes())) {
                    System.out.println(userQuery.get(0).getpatientHNId());
                    String authToken = tokenService.generateJWTToken(userQuery.get(0).getpatientHNId());

                    Map<String,String> payload = new HashMap<String,String>();
                    payload.put("authtoken", authToken);
                    payload.put("patientName", userQuery.get(0).getPatientFirstName());
                    InternalPayload returnPayload = new InternalPayload("0", "Okay", payload);
                    // String decodedjwt = tokenService.verifyJWTToken(authToken);
                    // System.out.println(decodedjwt);
                    return returnPayload;
                }
                else {
                    InternalPayload returnPayload = new InternalPayload("1", "Auth Failed");
                    return returnPayload;
                }
            }
            else {
                InternalPayload returnPayload = new InternalPayload("1", "User Not Found");
                return returnPayload;
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
            InternalPayload returnPayload = new InternalPayload("1", "Server Error");
            return returnPayload;
            // TODO: handle exception
        }
        
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
