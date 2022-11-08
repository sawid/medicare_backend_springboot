package com.medicare_backend.medicare_backend.route;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.medicare_backend.medicare_backend.controller.EmployeeController;
import com.medicare_backend.medicare_backend.schema.entity.Authentication;
import com.medicare_backend.medicare_backend.schema.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeRoute {
    
    @Autowired
    private EmployeeController employeeController;
    
    @PostMapping("/employee/register")
    public ResponseEntity<?> registerNewUser(@RequestHeader("authtoken") String authtoken, @RequestBody Employee user) {
        String data = employeeController.registerEmployee(authtoken, user);
        if (data == "Register Success") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
        
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> loginToEmployee(@RequestBody Authentication auth) {
        String data = employeeController.loginEmployee(auth);
        if (data != "Auth Failed" && data != "Error") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
        
    }

}
