package com.medicare_backend.medicare_backend.schema.relationship;

import javax.persistence.*;

@Entity
@Table(name = "PatientDisease")
public class PatientDisease {
    private long Id;
    private int patientHNId;
    private int diseaseId;

    public PatientDisease() {
    }

    public PatientDisease(int patientHNId, int diseaseId) {
        this.patientHNId = patientHNId;
        this.diseaseId = diseaseId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }

    @Column(name = "patientHNId", nullable = false)
    public int getPatientHNId() {
        return patientHNId;
    }

    public void setPatientHNId(int patientHNId) {
        this.patientHNId = patientHNId;
    }

    @Column(name = "getDiseaseId")
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public String toString() {
        return "PatientDisease [patientHNId=" + patientHNId + ", diseaseId=" + diseaseId + "]";
    }

}
