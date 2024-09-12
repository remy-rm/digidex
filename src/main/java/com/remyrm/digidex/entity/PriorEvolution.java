package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "condition" })
public class PriorEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonIgnore
    private Digimon digimon;

    @ManyToOne
    @JoinColumn(name = "digimon_prior_evolution_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Digimon digimonPriorEvolution;

    @JsonProperty("id")
    public Long getNextEvolutionId() {
        return digimonPriorEvolution != null ? digimonPriorEvolution.getId() : null;
    }

    @JsonProperty("name")
    public String getNextEvolutionName() {
        return digimonPriorEvolution != null ? digimonPriorEvolution.getName() : null;
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

    public Digimon getDigimonPriorEvolution() {
        return digimonPriorEvolution;
    }

    public void setDigimonPriorEvolution(Digimon digimonPriorEvolution) {
        this.digimonPriorEvolution = digimonPriorEvolution;
    }
}
