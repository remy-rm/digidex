package com.remyrm.digidex.dto;

public class PriorEvolutionDTO {

    private Long id;
    private String digimonName;
    private String condition;

    public PriorEvolutionDTO(String condition, Long id, String digimonName) {
        this.condition = condition;
        this.digimonName = digimonName;
        this.id = id;
    }

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

    public String getDigimonName() {
        return digimonName;
    }

    public void setDigimonName(String digimonName) {
        this.digimonName = digimonName;
    }
}
