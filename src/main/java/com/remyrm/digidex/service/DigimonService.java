package com.remyrm.digidex.service;

import com.remyrm.digidex.dto.DigimonDTO;
import com.remyrm.digidex.dto.NextEvolutionDTO;
import com.remyrm.digidex.dto.PriorEvolutionDTO;
import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.Mapper.NextEvolutionMapper;
import com.remyrm.digidex.service.Mapper.PriorEvolutionMapper;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    private final ImageDownloadService imageDownloadService;
    private final DigimonRepository digimonRepository;
    private final SearchDigimonByCriteriaService searchDigimonByCriteria;
    private final NextEvolutionMapper nextEvolutionMapper;
    private final PriorEvolutionMapper priorEvolutionMapper;

    @Autowired
    public DigimonService(DigimonRepository digimonRepository, SearchDigimonByCriteriaService searchDigimonByCriteria, ImageDownloadService imageDownloadService, NextEvolutionMapper nextEvolutionMapper, PriorEvolutionMapper priorEvolutionMapper) {
        super(Digimon.class, "digimon/", digimonRepository);
        this.digimonRepository = digimonRepository;
        this.searchDigimonByCriteria = searchDigimonByCriteria;
        this.imageDownloadService = imageDownloadService;
        this.nextEvolutionMapper = nextEvolutionMapper;
        this.priorEvolutionMapper = priorEvolutionMapper;
    }

    @Override
    public Digimon fetchEntityById(Long id) {
        Digimon digimon = (Digimon) super.fetchEntityById(id);

        if (digimon != null) {
            if (digimon.getPriorEvolutions() != null) {
                Set<PriorEvolution> priorEvolutionsSet = new HashSet<>();
                for (PriorEvolution priorEvolution : digimon.getPriorEvolutions()) {
                    PriorEvolution formattedPriorEvolution = new PriorEvolution();
                    formattedPriorEvolution.setDigimonPriorEvolution(priorEvolution.getId());
                    formattedPriorEvolution.setCondition(priorEvolution.getCondition());
                    formattedPriorEvolution.setDigimon(digimon);
                    priorEvolutionsSet.add(formattedPriorEvolution);
                }
                digimon.setPriorEvolutions(priorEvolutionsSet);
            }

            if (digimon.getNextEvolutions() != null) {
                Set<NextEvolution> nextEvolutionsSet = new HashSet<>();
                for (NextEvolution nextEvolution : digimon.getNextEvolutions()) {
                    NextEvolution formattedNextEvolution = new NextEvolution();
                    formattedNextEvolution.setDigimonNextEvolution(nextEvolution.getId());
                    formattedNextEvolution.setCondition(nextEvolution.getCondition());
                    formattedNextEvolution.setDigimon(digimon);
                    nextEvolutionsSet.add(formattedNextEvolution);
                }
                digimon.setNextEvolutions(nextEvolutionsSet);
            }

            if (digimon.getDescriptions() != null) {
                for (Description desc : digimon.getDescriptions()) {
                    desc.setDigimon(digimon);
                }
            }

            if (digimon.getImages() != null) {
                for (Image image : digimon.getImages()) {
                    try {
                        String localImagePath = imageDownloadService.downloadImage(image.getImage());
                        image.setImage(localImagePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image.setDigimon(digimon);
                }
            }

            if (digimon.getSkills() != null) {
                for (Skill skill : digimon.getSkills()) {
                    skill.setDigimon(digimon);
                }
            }
        }

        return digimon;
    }


    public List<Digimon> findAllByCursor(Long cursor, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("id").ascending());
        Page<Digimon> digimonPage;
        if (cursor != null) {
            digimonPage = digimonRepository.findByIdGreaterThan(cursor, pageable);
        } else {
            digimonPage = digimonRepository.findAll(pageable);
        }
        return digimonPage.getContent();
    }

    public List<Digimon> searchDigimonByCriteria(
            String query,
            String levelNames,
            String typeNames,
            String attributeNames,
            String fieldNames,
            Long cursor,
            int size
    ) {
        return searchDigimonByCriteria.searchDigimonByCriteria(
                query, levelNames, typeNames, attributeNames, fieldNames, cursor, size
        );
    }
    public DigimonDTO toDTO(Digimon digimon) {
        Set<NextEvolutionDTO> nextEvolutionDTOs = digimon.getNextEvolutions().stream()
                .map(nextEvolutionMapper::toDTO)
                .collect(Collectors.toSet()); // Utilisez Set ici
        Set<PriorEvolutionDTO> priorEvolutionDTOs = digimon.getPriorEvolutions().stream()
                .map(priorEvolutionMapper::toDTO)
                .collect(Collectors.toSet()); // Utilisez Set ici

        // Créez et retournez le DTO pour Digimon
        return new DigimonDTO(
                digimon.getId(),
                digimon.getName(),
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