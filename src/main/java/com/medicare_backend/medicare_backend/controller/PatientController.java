package com.medicare_backend.medicare_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/patients")
    public ResponseEntity<?> getPatient() {
        List<Patient> data = patientService.getPatient();
        if (!(data != null && data.isEmpty())) {
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @GetMapping(path = "/patients/findbyId/{id}")
    public ResponseEntity<?> getPatientmentById(@PathVariable("id") long patientHNId) {
        Optional<Patient> data = patientService.getPatientById(patientHNId);
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
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody Patient patient) {
        try {
            Patient _patient = patientService.updatePatient(id, patient);
            Patient updatePatient = patientRepository.save(_patient);
            return ResponseEntity.ok().body(updatePatient);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(patient);
        }

    }

}
