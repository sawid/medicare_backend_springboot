package com.medicare_backend.medicare_backend.schema.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patient")
public class Patient {
    private int patientHNId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String patientNationalId;
    private String patientPhoneNumber;
    private LocalTime patientBirthDate;
    private String patientLocation;
    private int patientBloodType;
    private int patientGender;

    public Patient() {
    }

    public Patient(int patientHNId, String patientFirstName, String patientMiddleName, String patientLastName,
            String patientNationalId, String patientPhoneNumber, LocalTime patientBirthDate, String patientLocation,
            int patientBloodType, int patientGender) {
        this.patientHNId = patientHNId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPatientHNId() {
        return this.patientHNId;
    }

    public int setPatientHNId() {
        return this.patientHNId;
    }

    @Column(name = "patientFirstName", nullable = false)
    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    @Column(name = "patientMiddleName")
    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    @Column(name = "patientLastName", nullable = false)
    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    @Column(name = "patientNationalId", nullable = false)
    public String getPatientNationalId() {
        return patientNationalId;
    }

    public void setPatientNationalId(String patientNationalId) {
        this.patientNationalId = patientNationalId;
    }

    @Column(name = "patientPhoneNumber", nullable = false)
    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    @Column(name = "patientBirthDate", nullable = false)
    public LocalTime getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(LocalTime patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    @Column(name = "patientLocation", nullable = false)
    public String getPatientLocation() {
        return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    @Column(name = "patientBloodType", nullable = false)
    public int getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(int patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    @Column(name = "patientGender", nullable = false)
    public int getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(int patientGender) {
        this.patientGender = patientGender;
    }

}
