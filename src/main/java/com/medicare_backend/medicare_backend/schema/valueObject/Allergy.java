package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.*;

@Entity
@Table(name = "allergy")
public class Allergy {
    private long allergyId;
    private String allergyName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getallergyId() {
        return allergyId;
    }
    public void setallergyId(long id) {
        this.allergyId = id;
    }

    public Allergy() {
    }

    public Allergy(String allergyName) {
        this.allergyName = allergyName;
    }

    @Column(name = "allergy_name", nullable = false)
    public String getAllergyName() {
        return this.allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }
}
