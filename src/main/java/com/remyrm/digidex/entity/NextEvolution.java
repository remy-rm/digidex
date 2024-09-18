package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "name", "condition"})
public class NextEvolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonIgnore
    private Digimon digimon;

    @Column(name = "digimon_next_evolution_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long digimonNextEvolution;

    @Column(name = "`condition`", columnDefinition = "TEXT")
    private String condition;


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

    public long getDigimonNextEvolution() {
        return digimonNextEvolution;
    }

    public void setDigimonNextEvolution(long digimonNextEvolution) {
        this.digimonNextEvolution = digimonNextEvolution;
    }
}
