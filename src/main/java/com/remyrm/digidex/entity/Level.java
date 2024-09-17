package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.remyrm.digidex.views.Views;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.DigimonSearchAll.class)
    private long id;

    @JsonProperty("name")
    @JsonView(Views.DigimonSearchAll.class)
    private String level;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "levels")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Digimon> digimon = new HashSet<>();

    public Set<Digimon> getDigimon() {
        return digimon;
    }

    public void setDigimon(Set<Digimon> digimon) {
        this.digimon = digimon;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String levels) {
        this.level = levels;
    }
}
