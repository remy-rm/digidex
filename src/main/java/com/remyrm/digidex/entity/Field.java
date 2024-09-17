package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.remyrm.digidex.common.HasImage;
import com.remyrm.digidex.views.Views;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field implements HasImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.DigimonSearchAll.class)
    private long id;
    @JsonView(Views.DigimonSearchAll.class)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @JsonProperty("href")
    @JsonView(Views.DigimonSearchAll.class)
    private String image;


    @ManyToMany(mappedBy = "fields")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Digimon> digimons = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Digimon> getDigimons() {
        return digimons;
    }

    public void setDigimons(Set<Digimon> digimons) {
        this.digimons = digimons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
