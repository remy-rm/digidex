package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.remyrm.digidex.views.Views;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Digimon {


    @Id
    @JsonView(Views.DigimonDetails.class)
    private Long id;
    @NotNull
    @JsonView(Views.DigimonDetails.class)
    private String name;
    @JsonView(Views.DigimonDetails.class)
    private Boolean xAntibody;
    @JsonView(Views.DigimonDetails.class)
    private String releaseDate;

    @ManyToMany
    @JoinTable(
            name = "digimon_levels",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id")
    )
    private Set<Level> levels = new HashSet<>();


    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.DigimonDetails.class)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.DigimonDetails.class)
    private Set<Description> descriptions = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.DigimonDetails.class)
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "digimon_fields",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    @JsonView(Views.DigimonDetails.class)
    private Set<Field> fields = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "digimon_types",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    @JsonView(Views.DigimonDetails.class)
    private Set<Type> types = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "digimon_attributes",
            joinColumns = @JoinColumn(name = "digimon_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    @JsonView(Views.DigimonDetails.class)
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.DigimonDetails.class)
    private Set<NextEvolution> nextEvolutions = new HashSet<>();

    @OneToMany(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.DigimonDetails.class)
    private Set<PriorEvolution> priorEvolutions = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<Long> getFields() {
        Set<Long> fieldIds = new HashSet<>();
        for (Field field : fields) {
            fieldIds.add(field.getId());
        }
        return fieldIds;
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

    public Set<Long> getLevels() {
        Set<Long> levelId = new HashSet<>();
        for (Level level : levels) {
            levelId.add(level.getId());
        }
        return levelId;
    }

    public void setLevels(Set<Level> levels) {
        this.levels = levels;
    }

    public Set<Long> getTypes() {
        Set<Long> typeId = new HashSet<>();
        for (Type type : types) {
            typeId.add(type.getId());
        }
        return typeId;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Set<Long> getAttributes() {
        Set<Long> attributeId = new HashSet<>();
        for (Attribute attribute : attributes) {
            attributeId.add(attribute.getId());
        }
        return attributeId;
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

        this.nextEvolutions.clear();
        if (nextEvolutions != null) {
            this.nextEvolutions.addAll(nextEvolutions);
        }
    }

}
