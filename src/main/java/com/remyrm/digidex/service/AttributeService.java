package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Attribute;
import com.remyrm.digidex.repository.AttributeRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;

@Service
public class AttributeService extends GenericFullService<Attribute, Long> {

    public AttributeService(AttributeRepository attributeRepository) {
        super(Attribute.class, "attribute/", attributeRepository);
    }
}
