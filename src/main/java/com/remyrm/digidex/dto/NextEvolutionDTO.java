package com.remyrm.digidex.dto;

public class NextEvolutionDTO {

    private Long id;
    private String condition;
    private String digimonName;


    public NextEvolutionDTO(Long id, String condition, String digimonName) {
        this.id = id;
        this.condition = condition;
        this.digimonName = digimonName;
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

    public String getDigimonName() {
        return digimonName;
    }

    public void setDigimonName(String digimonName) {
        this.digimonName = digimonName;
    }
}
