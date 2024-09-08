package com.remyrm.digidex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.*;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    private final LevelRepository levelRepository;
    private final TypeRepository typeRepository;
    private final AttributeRepository attributeRepository;
    private final FieldService fieldService;

    @Autowired
    public DigimonService(DigimonRepository digimonRepository,
                          LevelRepository levelRepository,
                          TypeRepository typeRepository,
                          AttributeRepository attributeRepository,
                          FieldService fieldService) {
        super(Digimon.class, "digimon/", digimonRepository);
        this.levelRepository = levelRepository;
        this.typeRepository = typeRepository;
        this.attributeRepository = attributeRepository;
        this.fieldService = fieldService;
    }

    public void createDigimonFromJson(JsonNode digimonJson) {
        // Créer un nouveau Digimon
        Digimon digimon = new Digimon();
        digimon.setName(digimonJson.get("name").asText());
        digimon.setxAntibody(digimonJson.get("xAntibody").asBoolean());
        digimon.setReleaseDate(digimonJson.get("releaseDate").asText());

        // Ajouter les descriptions
        ArrayNode descriptionsJson = (ArrayNode) digimonJson.get("descriptions");
        for (JsonNode descJson : descriptionsJson) {
            Description description = new Description();
            description.setOrigin(descJson.get("origin").asText());
            description.setLanguage(descJson.get("language").asText());
            description.setDescription(descJson.get("description").asText());
            description.setDigimon(digimon);
            digimon.getDescriptions().add(description);
        }

        // Ajouter les images
        ArrayNode imagesJson = (ArrayNode) digimonJson.get("images");
        for (JsonNode imgJson : imagesJson) {
            Image image = new Image();
            image.setImage(imgJson.get("href").asText());
            image.setTransparent(imgJson.get("transparent").asBoolean());
            image.setDigimon(digimon);
            digimon.getImages().add(image);
        }

        // Ajouter les levels
        ArrayNode levelsJson = (ArrayNode) digimonJson.get("levels");
        for (JsonNode levelJson : levelsJson) {
            Level level = levelRepository.findById(levelJson.get("id").asLong()).orElse(null);
            if (level != null) {
                digimon.getLevels().add(level);
            }
        }

        // Ajouter les types
        ArrayNode typesJson = (ArrayNode) digimonJson.get("types");
        for (JsonNode typeJson : typesJson) {
            Type type = typeRepository.findById(typeJson.get("id").asLong()).orElse(null);
            if (type != null) {
                digimon.getTypes().add(type);
            }
        }

        // Ajouter les attributes
        ArrayNode attributesJson = (ArrayNode) digimonJson.get("attributes");
        for (JsonNode attrJson : attributesJson) {
            Attribute attribute = attributeRepository.findById(attrJson.get("id").asLong()).orElse(null);
            if (attribute != null) {
                digimon.getAttributes().add(attribute);
            }
        }

        // Ajouter les fields
        ArrayNode fieldsJson = (ArrayNode) digimonJson.get("fields");
        for (JsonNode fieldJson : fieldsJson) {
            Field field = fieldService.findById(fieldJson.get("id").asLong()).orElse(null);
            if (field != null) {
                digimon.getFields().add(field);
            }
        }

        // Sauvegarder le Digimon
        save(digimon);
    }
}
