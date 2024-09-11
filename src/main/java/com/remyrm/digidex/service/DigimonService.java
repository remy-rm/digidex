package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    @Autowired
    private ImageDownloadService imageDownloadService;
    private final DigimonRepository digimonRepository;

    @Autowired
    public DigimonService(DigimonRepository digimonRepository) {
        super(Digimon.class, "digimon/", digimonRepository);
        this.digimonRepository = digimonRepository;
    }

    @Override
    public Digimon fetchEntityById(Long id) {
        Digimon digimon = (Digimon) super.fetchEntityById(id);

        if (digimon != null) {
            if (digimon.getPriorEvolutions() != null) {
                for (PriorEvolution priorEvolution : digimon.getPriorEvolutions()) {
                    priorEvolution.setDigimon(digimon);
                }
            }

            if (digimon.getNextEvolutions() != null) {
                for (NextEvolution nextEvolution : digimon.getNextEvolutions()) {
                    nextEvolution.setDigimon(digimon);
                }
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


}
