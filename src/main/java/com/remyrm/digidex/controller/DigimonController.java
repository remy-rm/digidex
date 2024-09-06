package com.remyrm.digidex.controller;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/digimon")
public class DigimonController extends GenericController<Digimon,Long > {

    public DigimonController(GenericFullService<Digimon, Long> digimonService) {
        super(digimonService);
    }
}
