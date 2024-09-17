package com.remyrm.digidex.service.Mapper;

import com.remyrm.digidex.dto.NextEvolutionDTO;
import com.remyrm.digidex.entity.NextEvolution;
import com.remyrm.digidex.repository.DigimonRepository;
import org.springframework.stereotype.Service;

@Service
public class NextEvolutionMapper {

    final DigimonRepository DigimonRepository;

    public NextEvolutionMapper(DigimonRepository DigimonRepository) {
        this.DigimonRepository = DigimonRepository;
    }

    public NextEvolutionDTO toDTO(NextEvolution nextEvolution) {

        String digimonName = DigimonRepository.findById(nextEvolution.getDigimonNextEvolution()).isPresent()
                ? DigimonRepository.findById(nextEvolution.getDigimonNextEvolution()).get().getName()
                : "Unknown";
        return new NextEvolutionDTO(nextEvolution.getCondition(), nextEvolution.getDigimonNextEvolution(), digimonName);
    }

}