package com.remyrm.digidex.controller;


import com.remyrm.digidex.entity.Type;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
public class TypeController extends GenericController<Type, Long> {

    public TypeController(GenericFullService<Type, Long> typeService) {
        super(typeService);
    }
}
