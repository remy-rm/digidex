package com.remyrm.digidex.service.Mapper;

import com.remyrm.digidex.dto.DigimonDTO;
import com.remyrm.digidex.dto.NextEvolutionDTO;
import com.remyrm.digidex.dto.PriorEvolutionDTO;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.entity.Image;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DigimonMapper {
    private final NextEvolutionMapper nextEvolutionMapper;
    private final PriorEvolutionMapper priorEvolutionMapper;

    public DigimonMapper(NextEvolutionMapper nextEvolutionMapper, PriorEvolutionMapper priorEvolutionMapper) {
        this.nextEvolutionMapper = nextEvolutionMapper;
        this.priorEvolutionMapper = priorEvolutionMapper;
    }

    public DigimonDTO toDTO(Digimon digimon) {
        Set<NextEvolutionDTO> nextEvolutionDTOs = digimon.getNextEvolutions().stream()
                .map(nextEvolutionMapper::toDTO)
                .collect(Collectors.toSet());
        Set<PriorEvolutionDTO> priorEvolutionDTOs = digimon.getPriorEvolutions().stream()
                .map(priorEvolutionMapper::toDTO)
                .collect(Collectors.toSet());


        String imageUrl = digimon.getImages().stream()
                .findFirst()
                .map(Image::getImage)
                .orElse(null);


        return new DigimonDTO(
                digimon.getId(),
                digimon.getName(),
                imageUrl,
                digimon.getLevels(),
                digimon.getTypes(),
                digimon.getAttributes(),
                digimon.getFields(),
                digimon.getReleaseDate(),
                digimon.getDescriptions(),
                digimon.getSkills(),
                nextEvolutionDTOs,
                priorEvolutionDTOs
        );
    }

}
