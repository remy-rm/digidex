package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Digimon {


    @Id
    private Long id;
    @NotNull
    private String name;
    private Boolean xAntibody;
    private String releaseDate;

    @ManyToMany
    @JoinTable(
            name = "digimon_levels",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id")
    )
    private Set<Level> levels = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Description> descriptions = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "digimon_fields",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private Set<Field> fields = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "digimon_types",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<Type> types = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "digimon_attributes",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NextEvolution> nextEvolutions = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PriorEvolution> priorEvolutions = new HashSet<>();


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

    public Set<Level> getLevels() {
        return levels;
    }

    public void setLevels(Set<Level> levels) {
        this.levels = levels;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
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

    public Set<PriorEvolution> getPriorEvolutions() {
        return priorEvolutions;
    }

    public void setPriorEvolutions(Set<PriorEvolution> priorEvolutions) {
        // Vider la collection existante au lieu de la remplacer
        this.priorEvolutions.clear();
        if (priorEvolutions != null) {
            this.priorEvolutions.addAll(priorEvolutions);
        }
    }

    public Set<NextEvolution> getNextEvolutions() {
        return nextEvolutions;
    }

    public void setNextEvolutions(Set<NextEvolution> nextEvolutions) {
        // Vider la collection existante au lieu de la remplacer
        this.nextEvolutions.clear();
        if (nextEvolutions != null) {
            this.nextEvolutions.addAll(nextEvolutions);
        }
    }

}
