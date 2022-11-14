package com.medicare_backend.medicare_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.repository.AppointmentRepository;
import com.medicare_backend.medicare_backend.repository.EmployeeRepository;
import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.schema.entity.Employee;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.schema.relationship.Appointment;
import com.medicare_backend.medicare_backend.service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/patients")
    public ResponseEntity<?> getPatient() {
        List<Patient> data = patientService.getPatient();
        for (Patient _patient : data) {
            _patient.setPatientPassword("mai bok eiei");
        }
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @GetMapping(path = "/patientsinfo/findbyId/{id}")
    public ResponseEntity<?> getPatientmentById(@PathVariable("id") long patientHNId) {
        Optional<Patient> data = patientService.getPatientById(patientHNId);

        data.get().setPatientPassword("mai bok eiei");

        if (data.isPresent()) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patients with Id : " + patientHNId + " Not Found");
        }
    }

    @PostMapping(path = "/addpatients")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        try {
            patientService.createPatient(patient);
            return ResponseEntity.ok().body(patient);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(patient);
        }
    }

    @PutMapping(path = "/updatePatient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable long id, @RequestBody Patient patient) {
        try {
            Patient _patient = patientService.updatePatient(id, patient);
            Patient updatePatient = patientRepository.save(_patient);
            return ResponseEntity.ok().body(updatePatient);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(patient);
        }

    }

    @GetMapping(path = "/getappointmentbyPatient/{id}")
    public ResponseEntity<?> getAppointmentByEmployeeId(@PathVariable("id") long patientId) {
        Integer patientCount = 0;
        try {

            List<Appointment> appointments = appointmentRepository
                    .findAppointmentByappointmentPatientId(patientId);
            if (appointments == null || appointments.isEmpty()) {
                return ResponseEntity.status(500).body("Employee with ID : " + patientId +
                        "is not exiting");
            }
            List<JSONObject> data = new ArrayList<>();
            for (Appointment a : appointments) {
                JSONObject object = new JSONObject();
                Optional<Employee> employee = employeeRepository.findById(a.getAppointmentDoctorId());
                object.put("appointmentDate", a.getAppointmentDate());
                object.put("appointmentTimeStart", a.getAppiontmentTimeStart());
                object.put("appointmentTimeEnd", a.getAppiontmentTimeEnd());
                object.put("patientFirstName", employee.get().getEmployeeFirstName());
                object.put("patientMiddleName", employee.get().getEmployeeMiddleName());
                object.put("patientLastName", employee.get().getEmployeeLastName());
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
