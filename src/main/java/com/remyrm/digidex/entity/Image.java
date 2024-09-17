package com.remyrm.digidex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.remyrm.digidex.common.HasImage;
import com.remyrm.digidex.views.Views;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements HasImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("href")
    @JsonView(Views.DigimonSearchAll.class)
    private String image;
    private boolean transparent;

    @ManyToOne
    @JoinColumn(name = "digimon_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Digimon digimon;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public Digimon getDigimon() {
        return digimon;
    }

    public void setDigimon(Digimon digimon) {
        this.digimon = digimon;
    }
}
