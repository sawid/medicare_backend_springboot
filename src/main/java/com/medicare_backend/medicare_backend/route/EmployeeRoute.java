package com.medicare_backend.medicare_backend.route;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.medicare_backend.medicare_backend.controller.EmployeeController;
import com.medicare_backend.medicare_backend.schema.entity.AuthenticationPatient;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.service.InternalPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeRoute {
    
    @Autowired
    private EmployeeController employeeController;
    
    @PostMapping("/employee/register")
    public ResponseEntity<?> registerEmployee(@RequestHeader("authtoken") String authtoken, @RequestBody Employee user) {
        InternalPayload data = employeeController.registerEmployee(authtoken, user);
        if (data.getStatusCode() == "0") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
        
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> loginToEmployee(@RequestBody AuthenticationPatient auth) {
        InternalPayload data = employeeController.loginEmployee(auth);
        if (data.getStatusCode() == "0") {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body(data);
        }
        
    }

}
