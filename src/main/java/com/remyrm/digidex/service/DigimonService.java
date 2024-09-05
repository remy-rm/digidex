package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigimonService extends GenericService<Digimon, Long> {

    @Autowired
    public DigimonService(DigimonRepository digimonRepository) {
        super(digimonRepository);
    }


}
