package com.medicare_backend.medicare_backend.schema.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PatientDisease")
public class PatientDisease {
    private int patientHNId;
    private int diseaseId;

    public PatientDisease() {
    }

    public PatientDisease(int patientHNId, int diseaseId) {
        this.patientHNId = patientHNId;
        this.diseaseId = diseaseId;
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
