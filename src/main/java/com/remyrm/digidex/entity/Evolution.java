package com.remyrm.digidex.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Evolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String digimon;

    @Column(name = "`condition`")
    private String condition;
    private String url;
    private String image;



    @ManyToMany(mappedBy = "priorEvolutions")
    private Set<Digimon> priorDigimons;

    @ManyToMany(mappedBy = "nextEvolutions")
    private Set<Digimon> nextDigimons;

    // Constructeur par défaut
    public Evolution() {
    }


    // Getters et Setters
    public Long getId() {
        return id;
    }

    public Set<Digimon> getNextDigimons() {
        return nextDigimons;
    }

    public void setNextDigimons(Set<Digimon> nextDigimons) {
        this.nextDigimons = nextDigimons;
    }

    public Set<Digimon> getPriorDigimons() {
        return priorDigimons;
    }

    public void setPriorDigimons(Set<Digimon> priorDigimons) {
        this.priorDigimons = priorDigimons;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDigimon() {
        return digimon;
    }

    public void setDigimon(String digimon) {
        this.digimon = digimon;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}