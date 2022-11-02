package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.*;

@Entity
@Table(name = "medicine")
public class Medicine {

    private long medicineId;
    private String medicineName;

    public Medicine() {
    }

    public Medicine(String medicineName) {
        this.medicineName = medicineName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

}
