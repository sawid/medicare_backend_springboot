package com.medicare_backend.medicare_backend.schema.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "allergic_to_medicine")
public class AllergicToMedicine {
    private long patientHNId;
    private int medicineId;

    public AllergicToMedicine() {

    }

    @Column(name = "patient_id", nullable = false)
    public long getPatientHNId() {
        return patientHNId;
    }

    public void setPatientHNId(long patientHNId) {
        this.patientHNId = patientHNId;
    }

    @Column(name = "medicine_id", nullable = false)
    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

}
