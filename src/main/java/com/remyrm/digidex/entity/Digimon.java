package com.remyrm.digidex.entity;

import jakarta.persistence.*;

@Entity
public class Digimon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Levels level;

    private String type;
    private String attribute;
    private String family;
    private String description;
    private String attack;
    private Integer size;
    private Integer weight;
    private Integer evolutionLevel;
    private Integer evolution;
    private String image;


    private String monsterType;

    // Constructeurs, getters et setters


    public Digimon() {
    }

    // Getters et Setters pour tous les champs

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }
}
