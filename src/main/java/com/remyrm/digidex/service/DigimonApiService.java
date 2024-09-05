package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import org.springframework.stereotype.Service;

@Service
public class DigimonApiService extends GenericApiService<Digimon, Long> {

    public DigimonApiService(DigimonRepository digimonRepository) {
        super(Digimon.class, "https://digi-api.com/api/v1/digimon/");
        this.repository = digimonRepository;
    }
}
