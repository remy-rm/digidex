package com.remyrm.digidex.service.digimonService;

import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class GetAllWithNameContainingService {

    private final DigimonRepository digimonRepository;
    private final LevelRepository levelRepository;
    private final AttributeRepository attributeRepository;
    private final TypeRepository typeRepository;
    private final FieldRepository fieldRepository;


    public GetAllWithNameContainingService(DigimonRepository digimonRepository, LevelRepository levelRepository, AttributeRepository attributeRepository, TypeRepository typeRepository, FieldRepository fieldRepository) {
        this.digimonRepository = digimonRepository;
        this.levelRepository = levelRepository;
        this.attributeRepository = attributeRepository;
        this.typeRepository = typeRepository;
        this.fieldRepository = fieldRepository;
    }

    public Map<String, Object> getAllWithNameContaining(String query) {

        Set<Digimon> digimons = digimonRepository.findByCriteria(query);
        Set<Level> levels = levelRepository.findByLevelContaining(query);
        Set<Attribute> attributes = attributeRepository.findByAttributeContaining(query);
        Set<Type> types = typeRepository.findByTypeContaining(query);
        Set<Field> fields = fieldRepository.findByNameContaining(query);

        Map<String, Object> result = new HashMap<>();
        result.put("digimons", digimons);
        result.put("levels", levels);
        result.put("attributes", attributes);
        result.put("types", types);
        result.put("fields", fields);

        return result;
    }
}
