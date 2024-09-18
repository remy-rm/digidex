package com.remyrm.digidex.service.digimonService;

import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.ImageDownloadService;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class DigimonFetchService extends GenericFullService<Digimon, Long> {

    private final ImageDownloadService imageDownloadService;

    public DigimonFetchService(DigimonRepository digimonRepository, ImageDownloadService imageDownloadService, RestTemplate restTemplate) {
        super(Digimon.class, "digimon/", digimonRepository, restTemplate, imageDownloadService);
        this.imageDownloadService = imageDownloadService;
    }

    @Override
    public Digimon fetchEntityById(Long id) {
        Digimon digimon = super.fetchEntityById(id);

        if (digimon != null) {
            formatPriorAndNextEvolutions(digimon);
            updateDescriptions(digimon);
            updateSkills(digimon);
            updateImages(digimon);
        }

        return digimon;
    }

    private void formatPriorAndNextEvolutions(Digimon digimon) {
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
    }

    private void updateDescriptions(Digimon digimon) {
        if (digimon.getDescriptions() != null) {
            for (Description desc : digimon.getDescriptions()) {
                desc.setDigimon(digimon);
            }
        }
    }

    private void updateImages(Digimon digimon) {
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
    }

    private void updateSkills(Digimon digimon) {
        if (digimon.getSkills() != null) {
            for (Skill skill : digimon.getSkills()) {
                skill.setDigimon(digimon);
            }
        }
    }
}
