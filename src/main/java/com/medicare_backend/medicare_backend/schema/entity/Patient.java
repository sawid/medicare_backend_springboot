package com.medicare_backend.medicare_backend.schema.entity;

import java.time.LocalDate;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.medicare_backend.medicare_backend.service.CustomIdGenerate;

@Entity
@Table(name = "patient")
public class Patient {

    private long patientHNId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String patientNationalId;
    private String patientPhoneNumber;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate patientBirthDate;
    private String patientLocation;
    private int patientBloodType;
    private int patientProfileIndex;
    private String patientPassword;
    private String patientMedicine;
    private String patientAllergy;
    private String patientDisease;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getpatientHNId() {
        return patientHNId;
    }

    public void setpatientHNId(Long id) {
        this.patientHNId = id;
    }

    public Patient() {
    }

    public Patient(String patientFirstName,
            String patientMiddleName,
            String patientLastName,
            String patientNationalId,
            String patientPhoneNumber,
            LocalDate patientBirthDate,
            String patientLocation,
            int patientBloodType,
            int patientProfileIndex,
            String patientPassword,
            String patientMedicine,
            String patientAllergy,
            String patientDisease) {
        this.patientFirstName = patientFirstName;
        this.patientMiddleName = patientMiddleName;
        this.patientLastName = patientLastName;
        this.patientNationalId = patientNationalId;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientBirthDate = patientBirthDate;
        this.patientLocation = patientLocation;
        this.patientBloodType = patientBloodType;
        this.patientProfileIndex = patientProfileIndex;
        this.patientPassword = patientPassword;
        this.patientMedicine = patientMedicine;
        this.patientAllergy = patientAllergy;
        this.patientDisease = patientDisease;
    }

    @Column(name = "patient_first_name", nullable = false)
    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    @Column(name = "patient_middle_name")
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
        return this.patientProfileIndex;
    }

    public void setPatientGender(int patientProfileIndex) {
        this.patientProfileIndex = patientProfileIndex;
    }

    @Column(name = "patient_password", nullable = false)
    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    @Column(name = "patient_medicaine", nullable = false)
    public String getPatientMedicine() {
        return patientMedicine;
    }

    public void setPatientMedicine(String patientMedicine) {
        this.patientMedicine = patientMedicine;
    }

    @Column(name = "patient_allergy", nullable = false)
    public String getPatientAllergy() {
        return patientAllergy;
    }

    public void setPatientAllergy(String patientAllergy) {
        this.patientAllergy = patientAllergy;
    }

    @Column(name = "patient_disease", nullable = false)
    public String getPatientDisease() {
        return patientDisease;
    }

    public void setPatientDisease(String patientDisease) {
        this.patientDisease = patientDisease;
    }

    @Override
    public String toString() {
        return "Patient [patientHNId=" + patientHNId + ", patientFirstName=" + patientFirstName + ", patientMiddleName="
                + patientMiddleName + ", patientLastName=" + patientLastName + ", patientNationalId="
                + patientNationalId + ", patientPhoneNumber=" + patientPhoneNumber + ", patientBirthDate="
                + patientBirthDate + ", patientLocation=" + patientLocation + ", patientBloodType=" + patientBloodType
                + ", patientGender=" + patientProfileIndex + "]";
    }

}