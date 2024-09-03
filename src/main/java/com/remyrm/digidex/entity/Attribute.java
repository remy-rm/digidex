package com.remyrm.digidex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String attributeName;

    public Attribute(Integer id, String attributeName) {
        this.id = id;
        this.attributeName = attributeName;
    }

    public Attribute() {
    }

    public long getId() {
        return id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
