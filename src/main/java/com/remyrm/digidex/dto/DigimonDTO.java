package com.remyrm.digidex.dto;

import com.remyrm.digidex.entity.Description;
import com.remyrm.digidex.entity.Skill;

import java.util.Set;

public class DigimonDTO {
    private Long id;
    private String name;
    private Set<Long> levels;
    private Set<Long> types;
    private Set<Long> attributes;
    private Set<Long> fields;
    private String releaseDate;
    private Set<Description> descriptions;
    private Set<Skill> skills;
    private Set<NextEvolutionDTO> nextEvolutions; // Utilisez Set ici
    private Set<PriorEvolutionDTO> priorEvolutions; // Utilisez Set ici

    public DigimonDTO() {
    }

    public DigimonDTO(Long id, String name, Set<Long> levels, Set<Long> types, Set<Long> attributes, Set<Long> fields, String releaseDate, Set<Description> descriptions, Set<Skill> skills, Set<NextEvolutionDTO> nextEvolutions, Set<PriorEvolutionDTO> priorEvolutions) {
        this.id = id;
        this.name = name;
        this.levels = levels;
        this.types = types;
        this.attributes = attributes;
        this.fields = fields;
        this.releaseDate = releaseDate;
        this.descriptions = descriptions;
        this.skills = skills;
        this.nextEvolutions = nextEvolutions;
        this.priorEvolutions = priorEvolutions;
    }

    // Getters et setters

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

    public Set<Long> getLevels() {
        return levels;
    }

    public void setLevels(Set<Long> levels) {
        this.levels = levels;
    }

    public Set<Long> getTypes() {
        return types;
    }

    public void setTypes(Set<Long> types) {
        this.types = types;
    }

    public Set<Long> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Long> attributes) {
        this.attributes = attributes;
    }

    public Set<Long> getFields() {
        return fields;
    }

    public void setFields(Set<Long> fields) {
        this.fields = fields;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<NextEvolutionDTO> getNextEvolutions() {
        return nextEvolutions;
    }

    public void setNextEvolutions(Set<NextEvolutionDTO> nextEvolutions) {
        this.nextEvolutions = nextEvolutions;
    }

    public Set<PriorEvolutionDTO> getPriorEvolutions() {
        return priorEvolutions;
    }

    public void setPriorEvolutions(Set<PriorEvolutionDTO> priorEvolutions) {
        this.priorEvolutions = priorEvolutions;
    }
}
