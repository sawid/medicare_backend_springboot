package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.*;

@Entity
@Table(name = "disease")
public class Disease {
    private long diseaseId;
    private String diseaseName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getId() {
        return diseaseId;
    }
    public void setId(long id) {
        this.diseaseId = id;
    }

    public Disease() {
    }

    public Disease(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Column(name = "diseaseName", nullable = false)
    public String getDiseaseName() {
        return this.diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}