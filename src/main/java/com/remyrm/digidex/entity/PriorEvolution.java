package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriorEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`condition`")
    private String condition;
    @Column(name = "digimon_prior_evolution_id")
    private Long digimonPriorEvolutionId;
    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonIgnore
    private Digimon digimon ;

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

    public Long getDigimonPriorEvolutionId() {
        return digimonPriorEvolutionId;
    }

    public void setDigimonPriorEvolutionId(Long digimonPriorEvolutionId) {
        this.digimonPriorEvolutionId = digimonPriorEvolutionId;
    }
}
