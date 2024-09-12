package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "description" })
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("name")
    private String attribute;

    @Column(columnDefinition = "TEXT")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
