package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NextEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`condition`", columnDefinition = "TEXT")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "digimon_next_evolution_id")
    @JsonIgnore
    private Digimon digimonNextEvolution;

    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonIgnore
    private Digimon digimon;

    @JsonProperty("digimonNextEvolution")
    public DigimonInfo getDigimonNextEvolutionInfo() {
        return digimonNextEvolution != null ? new DigimonInfo(digimonNextEvolution.getId(), digimonNextEvolution.getName()) : null;
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
}
