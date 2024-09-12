package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "condition" })
public class NextEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "digimon_next_evolution_id")
    @JsonIgnore
    private Digimon digimonNextEvolution;

    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonIgnore
    private Digimon digimon;

    @JsonProperty("id")
    public Long getNextEvolutionId() {
        return digimonNextEvolution != null ? digimonNextEvolution.getId() : null;
    }

    @JsonProperty("name")
    public String getNextEvolutionName() {
        return digimonNextEvolution != null ? digimonNextEvolution.getName() : null;
    }
    @Column(name = "`condition`", columnDefinition = "TEXT")
    private String condition;


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Digimon getDigimon() {
        return digimon;
    }

    public void setDigimon(Digimon digimon) {
        this.digimon = digimon;
    }

    public Digimon getDigimonNextEvolution() {
        return digimonNextEvolution;
    }

    public void setDigimonNextEvolution(Digimon digimonNextEvolution) {
        this.digimonNextEvolution = digimonNextEvolution;
    }



}
