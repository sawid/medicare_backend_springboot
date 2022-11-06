package com.medicare_backend.medicare_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare_backend.medicare_backend.repository.PatientRepository;
import com.medicare_backend.medicare_backend.schema.entity.Patient;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatient() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String patientHNId) {
        return patientRepository.findBypatientHNId(patientHNId);
    }

    public String createPatient(Patient patient) {
        patientRepository.save(patient);
        return "suscess";
    }

    public Patient updatePatient(String id, Patient patient) {
        Patient _patient = patientRepository.findBypatientHNId(id)
                .orElseThrow(() -> new Handler("Patient not exit with id" + id));
        _patient.setPatientFirstName(patient.getPatientFirstName());

        return _patient;

    }
}
