package com.remyrm.digidex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Family {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String familyName;

    public Family(Integer id, String familyName) {
        this.id = id;
        this.familyName = familyName;
    }

    public Family() {
    }

    public long getId() {
        return id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
