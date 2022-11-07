package com.medicare_backend.medicare_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare_backend.medicare_backend.schema.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findBypatientHNId(String patientHNId);

    List<Patient> findByPatientNationalId(String patientNationalId);
}