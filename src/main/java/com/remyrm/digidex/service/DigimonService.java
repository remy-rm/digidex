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

}
