package com.medicare_backend.medicare_backend.schema.relationship;

import javax.persistence.Column;

public class PatientAllergy {
    private int allergyId;
    private int patientHNId;

    public PatientAllergy() {
    }

    public PatientAllergy(int allergyId, int patientHNId) {
        this.allergyId = allergyId;
        this.patientHNId = patientHNId;
    }

    @Column(name = "allergyId")
    public int getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(int allergyId) {
        this.allergyId = allergyId;
    }

    @Column(name = "patientHNId", nullable = false)
    public int getPatientHNId() {
        return patientHNId;
    }

    public void setPatientHNId(int patientHNId) {
        this.patientHNId = patientHNId;
    }

    @Override
    public String toString() {
        return "PatientAllergy [allergyId=" + allergyId + ", patientHNId=" + patientHNId + "]";
    }

}
