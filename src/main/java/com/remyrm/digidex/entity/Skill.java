package com.remyrm.digidex.entity;

import jakarta.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String skill;
    private String translation;
    @Column(columnDefinition = "TEXT")

    private String description;
    @ManyToOne
    @JoinColumn(name = "digimon_id")
    private Digimon digimon;


    public long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }
// Getters et Setters
}
