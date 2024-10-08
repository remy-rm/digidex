package com.remyrm.digidex.controller;

import com.remyrm.digidex.entity.Field;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field")
public class FieldController extends GenericController<Field, Long> {

    public FieldController(GenericFullService<Field, Long> fieldService) {
        super(fieldService);
    }
}
