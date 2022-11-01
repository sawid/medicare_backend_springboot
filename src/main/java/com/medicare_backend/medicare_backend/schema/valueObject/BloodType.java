package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blood_type")
public class BloodType {
    private long bloodTypeId;
    private String bloodTypeName;

    public BloodType() {

    }

    @Column(name = "blood_type_id", nullable = false)
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
