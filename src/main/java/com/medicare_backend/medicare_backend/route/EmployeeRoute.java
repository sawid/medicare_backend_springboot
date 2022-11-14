package com.medicare_backend.medicare_backend.route;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.medicare_backend.medicare_backend.controller.EmployeeController;
import com.medicare_backend.medicare_backend.repository.AppointmentRepository;
import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.schema.entity.AuthenticationPatient;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.service.InternalPayload;
import com.medicare_backend.medicare_backend.service.PatientService;
import com.medicare_backend.medicare_backend.service.PatientService;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class EmployeeRoute {

    private PatientService patientService;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

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
        if (!(data != null && !data.isPresent())) {
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

    @GetMapping(path = "/employee/findappointment/{id}")
    public ResponseEntity<?> employeeAppointment(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
        try {
            List<Appointment> _appointment = employeeController.getAppointmentbyEmployeeId(employeeId);
            List<JSONObject> data = new ArrayList<>();
            Integer patientCount = 0;
            if (_appointment != null && !_appointment.isEmpty()) {
                for (Appointment _data : _appointment) {
                    Optional<Patient> patient = patientService.getPatientById(_data.getAppointmentPatientId());
                    JSONObject object = new JSONObject();
                    object.put("appointmentDate", _data.getAppointmentDate());
                    object.put("appointmentTimeStart", _data.getAppiontmentTimeStart());
                    object.put("appointmentTimeEnd", _data.getAppiontmentTimeEnd());
                    object.put("patientFirstName", patient.get().getPatientFirstName());
                    object.put("patientMiddleName", patient.get().getPatientMiddleName());
                    object.put("patientLastName", patient.get().getPatientLastName());
                    data.add(object);
                    patientCount++;
                }
                JSONObject _patientcount = new JSONObject();
                _patientcount.put("patientcount", _patientcount);
                data.add(_patientcount);
                return ResponseEntity.ok().body(data);
            } else {
                return ResponseEntity.status(500).body("Appointmentid not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(employee);
        }
    }

    @GetMapping(path = "/getappointmentbyEmployee/{id}")
    public ResponseEntity<?> getAppointmentByEmployeeId(@PathVariable("id") long employeeId) {
        Integer patientCount = 0;
        try {

            List<Appointment> appointments = appointmentRepository
                    .findAppointmentByappointmentDoctorId(employeeId);
            if (appointments == null || appointments.isEmpty()) {
                return ResponseEntity.status(500).body("Employee with ID : " + employeeId +
                        "is not exiting");
            }
            List<JSONObject> data = new ArrayList<>();
            for (Appointment a : appointments) {
                JSONObject object = new JSONObject();
                Optional<Patient> patient = patientRepository.findById(a.getAppointmentPatientId());
                object.put("appointmentDate", a.getAppointmentDate());
                object.put("appointmentTimeStart", a.getAppiontmentTimeStart());
                object.put("appointmentTimeEnd", a.getAppiontmentTimeEnd());
                object.put("patientFirstName", patient.get().getPatientFirstName());
                object.put("patientMiddleName", patient.get().getPatientMiddleName());
                object.put("patientLastName", patient.get().getPatientLastName());
                data.add(object);
                patientCount++;

            }
            JSONObject _object = new JSONObject();
            _object.put("patient", patientCount);
            data.add(_object);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(e);

        }

    }

}
