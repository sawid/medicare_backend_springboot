package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blood_type")
public class BloodType {
    private long bloodTypeId;
    private String bloodTypeName;

    public BloodType() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(long bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    @Column(name = "blood_type_name", nullable = false)
    public String getBloodTypeName() {
        return bloodTypeName;
    }

    public void setBloodTypeName(String bloodTypeName) {
        this.bloodTypeName = bloodTypeName;
    }

}
