package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    @Autowired
    public DigimonService(DigimonRepository digimonRepository) {
        super(Digimon.class, "digimon/", digimonRepository);
    }

}
