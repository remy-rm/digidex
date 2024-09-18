package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Field;
import com.remyrm.digidex.repository.FieldRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FieldService extends GenericFullService<Field, Long> {

    public FieldService(FieldRepository fieldRepository, RestTemplate restTemplate, ImageDownloadService imageDownloadService) {
        super(Field.class, "field/", fieldRepository, restTemplate, imageDownloadService);
    }
}
