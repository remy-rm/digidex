package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.entity.Field;
import com.remyrm.digidex.repository.FieldRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FieldService {

    final private FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Transactional
    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    public Field getFieldById(long id) {
        return (Field) fieldRepository.findById(id).orElse(null);
    }
}
