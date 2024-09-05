package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Field;
import com.remyrm.digidex.repository.FieldRepository;
import org.springframework.stereotype.Service;

@Service
public class FieldApiService extends GenericApiService<Field, Long> {

    public FieldApiService(FieldRepository fieldRepository) {
        super(Field.class, "https://digi-api.com/api/v1/field/");
        this.repository = fieldRepository;
    }
}
