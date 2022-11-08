package com.medicare_backend.medicare_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.schema.entity.Authentication;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.service.AuthenticationService;
import com.medicare_backend.medicare_backend.service.TokenAuthenticationService;

@Service
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private AuthenticationService authservice;

    private TokenAuthenticationService tokenService;

    public String registerEmployee(String authtoken, Employee employee) {
        try {
            String authEmployeeID = tokenService.verifyJWTToken(authtoken);
            if (authEmployeeID == "error") {
                return "Auth Time Out";
            }
            // System.out.println(authEmployeeID);
            List<Employee> adminIsMatch = employeeRepository.findByEmployeeNationalId(authEmployeeID);
            if (adminIsMatch != null && adminIsMatch.isEmpty()) {
                return "Auth Employee not found";
            }
            if (adminIsMatch.get(0).getEmployeeIsAdmin() != true) {
                return "No Permission";
            }
            List<Employee> employeeIsMatch = employeeRepository.findByEmployeeNationalId(employee.getEmployeeNationalId());
            if (employeeIsMatch != null && employeeIsMatch.isEmpty()) {
                System.out.println(employeeIsMatch);
                byte[] hash = authservice.getEncryptedPassword(employee.getEmployeePassword(), "salt".getBytes());
                employee.setEmployeePassword(authservice.bytesToHex(hash));
                employeeRepository.save(employee);
                return "Register Success";
            } else {
                return "Already User";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "Register Error";
        }
    }

    public String loginEmployee(Authentication auth) {
        String returnString = "";
        try {
            List<Employee> userQuery = employeeRepository.findByEmployeeNationalId(auth.getUsername());
            if (!(userQuery != null && userQuery.isEmpty())) {
                String userPassword = userQuery.get(0).getEmployeePassword();
                byte[] passwordToByte = authservice.hexToByte(userPassword);
                if(authservice.authenticate(auth.getPassword(), passwordToByte, "salt".getBytes())) {
                    System.out.println(userQuery.get(0).getEmployeeNationalId());
                    String authToken = tokenService.generateJWTToken(userQuery.get(0).getEmployeeNationalId());
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
                returnString = "Employee Not Found";
            }
            
            return returnString;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return "Error";
    }

}
