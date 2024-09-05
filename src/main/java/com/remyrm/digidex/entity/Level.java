package com.remyrm.digidex.entity;

import jakarta.persistence.*;

@Entity
public class Level {

    @ManyToOne
    @JoinColumn(name = "digimon_id")

    private Digimon digimon;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String level;


    public long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String levels) {
        this.level = levels;
    }
}
