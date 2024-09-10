package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    @Autowired
    private ImageDownloadService imageDownloadService;

    @Autowired
    public DigimonService(DigimonRepository digimonRepository) {
        super(Digimon.class, "digimon/", digimonRepository);
    }

    @Override
    public Digimon fetchEntityById(Long id) {
        Digimon digimon = (Digimon) super.fetchEntityById(id);

        if (digimon != null) {
            if (digimon.getPriorEvolutions() != null) {
                Set<PriorEvolution> priorEvolutionsSet = new HashSet<>();
                for (PriorEvolution priorEvolution : digimon.getPriorEvolutions()) {
                    PriorEvolution formattedPriorEvolution = new PriorEvolution();
                    formattedPriorEvolution.setDigimonPriorEvolutionId(priorEvolution.getId());
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
                    formattedNextEvolution.setDigimonNextEvolutionId(nextEvolution.getId());
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
}
