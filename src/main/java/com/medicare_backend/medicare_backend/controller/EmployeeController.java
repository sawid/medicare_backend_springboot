package com.medicare_backend.medicare_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.schema.entity.AuthenticationPatient;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.service.AuthenticationService;
import com.medicare_backend.medicare_backend.service.InternalPayload;
import com.medicare_backend.medicare_backend.service.TokenAuthenticationService;

@Service
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private AuthenticationService authservice;

    private TokenAuthenticationService tokenService;

    public InternalPayload registerEmployee(String authtoken, Employee employee) {
        try {
            String authEmployeeID = tokenService.verifyJWTToken(authtoken);
            if (authEmployeeID == "error") {
                InternalPayload returnPayload = new InternalPayload("1", "Auth Time Out");
                return returnPayload;
            }
            // System.out.println(authEmployeeID);
            List<Employee> adminIsMatch = employeeRepository.findByEmployeeNationalId(authEmployeeID);
            if (adminIsMatch != null && adminIsMatch.isEmpty()) {
                InternalPayload returnPayload = new InternalPayload("1", "Auth Employee not found");
                return returnPayload;
            }
            if (adminIsMatch.get(0).getEmployeeIsAdmin() != true) {
                InternalPayload returnPayload = new InternalPayload("1", "No Permission");
                return returnPayload;
            }
            List<Employee> employeeIsMatch = employeeRepository
                    .findByEmployeeNationalId(employee.getEmployeeNationalId());
            if (employeeIsMatch != null && employeeIsMatch.isEmpty()) {
                System.out.println(employeeIsMatch);
                byte[] hash = authservice.getEncryptedPassword(employee.getEmployeePassword(), "salt".getBytes());
                employee.setEmployeePassword(authservice.bytesToHex(hash));
                employeeRepository.save(employee);
                InternalPayload returnPayload = new InternalPayload("0", "Register Success");
                return returnPayload;
            } else {
                InternalPayload returnPayload = new InternalPayload("1", "Already Exist Employee");
                return returnPayload;
            }
        } catch (Exception e) {
            System.out.println(e);
            InternalPayload returnPayload = new InternalPayload("1", "Server Error");
            return returnPayload;
        }
    }

    public InternalPayload loginEmployee(AuthenticationPatient auth) {
        try {
            List<Employee> userQuery = employeeRepository.findByEmployeeNationalId(auth.getNationalCardId());
            if (!(userQuery != null && userQuery.isEmpty())) {
                String userPassword = userQuery.get(0).getEmployeePassword();
                byte[] passwordToByte = authservice.hexToByte(userPassword);
                if (authservice.authenticate(auth.getPassword(), passwordToByte, "salt".getBytes())) {
                    System.out.println(userQuery.get(0).getEmployeeNationalId());
                    String authToken = tokenService.generateJWTToken(userQuery.get(0).getEmployeeNationalId());
                    String decodedjwt = tokenService.verifyJWTToken(authToken);
                    System.out.println(decodedjwt);
                    Map<String, String> payload = new HashMap<String, String>();
                    payload.put("authtoken", authToken);
                    payload.put("employeeFirstName", userQuery.get(0).getEmployeeFirstName());
                    // payload.put("employeeMiddleName", userQuery.get(0).getEmployeeMiddleName());
                    // payload.put("employeeLastName", userQuery.get(0).getEmployeeLastName());
                    payload.put("employeeNationalId", userQuery.get(0).getEmployeeNationalId());
                    // payload.put("employeePhoneNumber",
                    // userQuery.get(0).getEmployeePhoneNumber(//));
                    payload.put("employeeRole", "TempRole");
                    // payload.put("employeeDepartment", "TempDepartment");
                    InternalPayload returnPayload = new InternalPayload("0", "Okay", payload);
                    return returnPayload;
                } else {
                    InternalPayload returnPayload = new InternalPayload("1", "Auth Failed");
                    return returnPayload;
                }
            } else {
                InternalPayload returnPayload = new InternalPayload("1", "Employee Not Found");
                return returnPayload;
            }

        } catch (Exception e) {
            System.out.println(e);
            InternalPayload returnPayload = new InternalPayload("1", "Server Error");
            return returnPayload;
            // TODO: handle exception
        }

    }

}
