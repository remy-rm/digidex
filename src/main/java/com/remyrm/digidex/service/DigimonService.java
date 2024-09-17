package com.remyrm.digidex.service;

import com.remyrm.digidex.dto.DigimonDTO;
import com.remyrm.digidex.dto.NextEvolutionDTO;
import com.remyrm.digidex.dto.PriorEvolutionDTO;
import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.*;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    private final ImageDownloadService imageDownloadService;
    private final DigimonRepository digimonRepository;
    private final SearchDigimonByCriteriaService searchDigimonByCriteria;
    private final NextEvolutionMapper nextEvolutionMapper;
    private final PriorEvolutionMapper priorEvolutionMapper;
    private final LevelRepository levelRepository;
    private final AttributeRepository attributeRepository;
    private final TypeRepository typeRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public DigimonService(DigimonRepository digimonRepository, SearchDigimonByCriteriaService searchDigimonByCriteria, ImageDownloadService imageDownloadService, NextEvolutionMapper nextEvolutionMapper, PriorEvolutionMapper priorEvolutionMapper, LevelRepository levelRepository, AttributeRepository attributeRepository, TypeRepository typeRepository, FieldRepository fieldRepository) {
        super(Digimon.class, "digimon/", digimonRepository);
        this.digimonRepository = digimonRepository;
        this.searchDigimonByCriteria = searchDigimonByCriteria;
        this.imageDownloadService = imageDownloadService;
        this.nextEvolutionMapper = nextEvolutionMapper;
        this.priorEvolutionMapper = priorEvolutionMapper;
        this.levelRepository = levelRepository;
        this.attributeRepository = attributeRepository;
        this.typeRepository = typeRepository;
        this.fieldRepository = fieldRepository;
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

    public List<DigimonDTO> searchDigimonByCriteria(
            String query,
            String levelNames,
            String typeNames,
            String attributeNames,
            String fieldNames,
            Long cursor,
            int size
    ) {
        List<Digimon> digimons = searchDigimonByCriteria.searchDigimonByCriteria(
                query, levelNames, typeNames, attributeNames, fieldNames, cursor, size
        );
        return digimons.stream()
                .map(this::toDTO) // Convertir chaque Digimon en DigimonDTO
                .collect(Collectors.toList());
    }


    public DigimonDTO toDTO(Digimon digimon) {
        Set<NextEvolutionDTO> nextEvolutionDTOs = digimon.getNextEvolutions().stream()
                .map(nextEvolutionMapper::toDTO)
                .collect(Collectors.toSet());
        Set<PriorEvolutionDTO> priorEvolutionDTOs = digimon.getPriorEvolutions().stream()
                .map(priorEvolutionMapper::toDTO)
                .collect(Collectors.toSet());

        // Extraire l'URL de la première image
        String imageUrl = digimon.getImages().stream()
                .findFirst() // Récupère la première image, s'il y en a une
                .map(Image::getImage) // On extrait l'URL de l'image
                .orElse(null); // Si aucune image, retourne null

        // Créez et retournez le DTO pour Digimon
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


    public Map<String, Object> searchByCriteria(String query) {
        // Trouver les Digimon correspondant au critère
        Set<Digimon> digimons = digimonRepository.findByCriteria(query);

        // Trouver les entités associées correspondant au critère
        Set<Level> levels = levelRepository.findByLevelContaining(query);
        Set<Attribute> attributes = attributeRepository.findByAttributeContaining(query);
        Set<Type> types = typeRepository.findByTypeContaining(query);
        Set<Field> fields = fieldRepository.findByNameContaining(query);

        // Créer un résultat combiné
        Map<String, Object> result = new HashMap<>();
        result.put("digimons", digimons);
        result.put("levels", levels);
        result.put("attributes", attributes);
        result.put("types", types);
        result.put("fields", fields);

        return result;
    }
}