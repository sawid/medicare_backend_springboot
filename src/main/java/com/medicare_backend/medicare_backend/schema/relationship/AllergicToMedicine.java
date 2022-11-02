package com.medicare_backend.medicare_backend.schema.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allergic_to_medicine")
public class AllergicToMedicine {
    private long allergicToMedicineId;
    private long patientHNId;
    private int medicineId;

    public AllergicToMedicine() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAllergicToMedicineId() {
        return allergicToMedicineId;
    }

    public void setAllergicToMedicineId(long allergicToMedicineId) {
        this.allergicToMedicineId = allergicToMedicineId;
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
