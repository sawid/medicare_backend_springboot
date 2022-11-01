package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.*;

@Entity
@Table(name = "allergy")
public class Allergy {
    private long allergyId;
    private String allergyName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getId() {
        return allergyId;
    }
    public void setId(long id) {
        this.allergyId = id;
    }

    public Allergy() {
    }

    public Allergy(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getAllergyName() {
        return this.allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }
}
