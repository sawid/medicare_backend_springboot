package com.medicare_backend.medicare_backend.route;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.medicare_backend.medicare_backend.controller.EmployeeController;
import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.schema.entity.AuthenticationPatient;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.service.InternalPayload;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeRoute {

    @Autowired
    private EmployeeController employeeController;

    @PostMapping("/employee/register")
    public ResponseEntity<?> registerEmployee(@RequestHeader("authtoken") String authtoken,
            @RequestBody Employee user) {
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

    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employeeController.saveEmployee(employee);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping(path = "/employee")
    public ResponseEntity<?> getEmployee() {
        List<Employee> data = employeeController.getEmployee();
        for (Employee _employee : data) {
            _employee.setEmployeePassword("maibokeiei");
        }
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @GetMapping(path = "/employeeinfo/findbyId/{id}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable("id") long employeeid) {
        Optional<Employee> data = employeeController.getEmployeeById(employeeid);
        data.get().setEmployeePassword("maibokeiei");
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @PutMapping(path = "/employeeinfo/findbyId/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
        try {
            Employee _employee = employeeController.updatEmployee(employeeId, employee);
            Employee updatEmployee = employeeController.saveEmployee(_employee);
            return ResponseEntity.ok().body(updatEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(employee);
        }
    }
}
