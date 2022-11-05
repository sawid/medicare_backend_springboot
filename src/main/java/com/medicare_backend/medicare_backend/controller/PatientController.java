package com.medicare_backend.medicare_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicare_backend.medicare_backend.schema.entity.Patient;
import com.medicare_backend.medicare_backend.service.PatientService;

@RestController
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping(path = "/patients")
    public ResponseEntity<?> getPatient(){
        List<Patient> data = patientService.getPatient();
        if(!(data != null && data.isEmpty())){
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patient List Not Found");
        }
    }

    @GetMapping(path = "/patients/findbyId/{id}") //mockup
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Long patientHNId){
        Optional<Patient> data = patientService.getPatientById(patientHNId);
        if(data.isPresent()){
            return ResponseEntity.ok().body(data);
        } else {
            return ResponseEntity.status(500).body("Patients with Id : " + patientHNId + " Not Found");
        }
    }
}
