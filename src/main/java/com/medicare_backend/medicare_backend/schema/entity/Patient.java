package com.medicare_backend.medicare_backend.schema.entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    private long patientHNId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String patientNationalId;
    private String patientPhoneNumber;
    private LocalDate patientBirthDate;
    private String patientLocation;
    private int patientBloodType;
    private int patientGender;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getpatientHNId() {
        return patientHNId;
    }
    public void setpatientHNId(long id) {
        this.patientHNId = id;
    }

    public Patient() {
    }

    public Patient( String patientFirstName, 
                    String patientMiddleName, 
                    String patientLastName, 
                    String patientNationalId, 
                    String patientPhoneNumber, 
                    LocalDate patientBirthDate, 
                    String patientLocation, 
                    int patientBloodType, 
                    int patientGender) {
        this.patientFirstName = patientFirstName;
        this.patientMiddleName = patientMiddleName;
        this.patientLastName = patientLastName;
        this.patientNationalId = patientNationalId;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientBirthDate = patientBirthDate;
        this.patientLocation = patientLocation;
        this.patientBloodType = patientBloodType;
        this.patientGender = patientGender;
    }

    @Column(name = "patient_first_name", nullable = false)
    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    @Column(name = "patient_middle_name", nullable = false)
    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    @Column(name = "patient_last_name", nullable = false)
    public String getPatientLastName() {
        return this.patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    @Column(name = "patient_national_id", nullable = false)
    public String getPatientNationalId() {
        return this.patientNationalId;
    }

    public void setPatientNationalId(String patientNationalId) {
        this.patientNationalId = patientNationalId;
    }

    @Column(name = "patient_phone_number", nullable = false)
    public String getPatientPhoneNumber() {
        return this.patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    @Column(name = "patient_birth_date", nullable = false)
    public LocalDate getPatientBirthDate() {
        return this.patientBirthDate;
    }

    public void setPatientBirthDate(LocalDate patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    @Column(name = "patient_location", nullable = false)
    public String getPatientLocation() {
        return this.patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    @Column(name = "patient_blood_type", nullable = false)
    public int getPatientBloodType() {
        return this.patientBloodType;
    }

    public void setPatientBloodType(int patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    @Column(name = "patient_gender", nullable = false)
    public int getPatientGender() {
        return this.patientGender;
    }

    public void setPatientGender(int patientGender) {
        this.patientGender = patientGender;
    }

}