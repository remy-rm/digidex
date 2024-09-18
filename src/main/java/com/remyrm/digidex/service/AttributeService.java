package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Attribute;
import com.remyrm.digidex.repository.AttributeRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AttributeService extends GenericFullService<Attribute, Long> {

    public AttributeService(AttributeRepository attributeRepository
            , RestTemplate restTemplate, ImageDownloadService imageDownloadService) {
        super(Attribute.class, "attribute/", attributeRepository, restTemplate, imageDownloadService);
    }
}
