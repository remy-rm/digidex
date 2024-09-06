package com.remyrm.digidex.controller;

import com.remyrm.digidex.entity.Attribute;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attribute")
public class AttributeController extends GenericController<Attribute, Long> {

    public AttributeController(GenericFullService<Attribute, Long> attributeService) {
        super(attributeService);
    }
}
