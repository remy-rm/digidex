package com.remyrm.digidex.service.Mapper;

import com.remyrm.digidex.dto.PriorEvolutionDTO;
import com.remyrm.digidex.entity.PriorEvolution;
import com.remyrm.digidex.repository.DigimonRepository;
import org.springframework.stereotype.Service;

@Service
public class PriorEvolutionMapper {

    final DigimonRepository digimonRepository;

    public PriorEvolutionMapper(DigimonRepository digimonRepository) {
        this.digimonRepository = digimonRepository;
    }

    public PriorEvolutionDTO toDTO(PriorEvolution priorEvolution) {
        String digimonName = digimonRepository.findById(priorEvolution.getId()).isPresent()
                ? digimonRepository.findById(priorEvolution.getId()).get().getName()
                : "Unknown";

        return new PriorEvolutionDTO(priorEvolution.getCondition(), digimonName, priorEvolution.getId());
    }

}
