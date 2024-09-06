package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Digimon {

    @Id
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "digimon_prior_evolutions",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "prior_evolution_id")
    )
    private Set<Evolution> priorEvolutions;

    @ManyToMany
    @JoinTable(
            name = "digimon_next_evolutions",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "next_evolution_id")
    )
    private Set<Evolution> nextEvolutions;

    private String name;
    private Boolean xAntibody;
    private String releaseDate;


    @ManyToMany
    @JoinTable(
            name = "digimon_levels",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id")
    )
    private Set<Level> level;

    @ManyToOne
    private Type types;

    @ManyToMany
    @JoinTable(
            name = "digimon_attributes",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes;

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images;

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Description> descriptions;

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "digimon_fields",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private Set<Field> fields;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getxAntibody() {
        return xAntibody;
    }

    public void setxAntibody(Boolean xAntibody) {
        this.xAntibody = xAntibody;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Level> getLevel() {
        return level;
    }

    public void setLevel(Set<Level> level) {
        this.level = level;
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
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

    public Set<Evolution> getPriorEvolutions() {
        return priorEvolutions;
    }

    public void setPriorEvolutions(Set<Evolution> priorEvolutions) {
        this.priorEvolutions = priorEvolutions;
    }

    public Set<Evolution> getNextEvolutions() {
        return nextEvolutions;
    }

    public void setNextEvolutions(Set<Evolution> nextEvolutions) {
        this.nextEvolutions = nextEvolutions;
    }
}
