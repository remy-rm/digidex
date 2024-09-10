package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NextEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`condition`" , columnDefinition = "TEXT")
    private String condition;
    @JoinColumn(name="digimon_next_evolution_id")
    private Long digimonNextEvolutionId;

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

    public Long getDigimonNextEvolutionId() {
        return digimonNextEvolutionId;
    }

    public void setDigimonNextEvolutionId(Long digimonNextEvolutionId) {
        this.digimonNextEvolutionId = digimonNextEvolutionId;
    }

}
