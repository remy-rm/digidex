package com.remyrm.digidex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Levels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String levels;

    public Levels(Integer id, String levels ) {
        this.id = id;
        this.levels = levels;
    }
    public Levels() {}
    public long getId() {
        return id;
    }

    public String getLevels() {
        return levels;
    }
    public void setLevels(String levels) {
        this.levels = levels;
    }
}
