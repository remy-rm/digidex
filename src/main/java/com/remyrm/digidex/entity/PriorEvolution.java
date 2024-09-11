package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriorEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`condition`", columnDefinition = "TEXT")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonIgnore
    private Digimon digimon;

    @ManyToOne
    @JoinColumn(name = "digimon_prior_evolution_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Digimon digimonPriorEvolution;

    @JsonProperty("digimonPriorEvolution")
    public DigimonInfo getDigimonPriorEvolutionInfo() {
        return digimonPriorEvolution != null ? new DigimonInfo(digimonPriorEvolution.getId(), digimonPriorEvolution.getName()) : null;
    }

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


    private static class DigimonInfo {
        private Long id;
        private String name;

        public DigimonInfo(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
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
