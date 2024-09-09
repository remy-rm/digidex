package com.remyrm.digidex.service.genericService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remyrm.digidex.common.HasImage;
import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.service.ImageDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class GenericApiService<T, ID extends Serializable> {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected JpaRepository<T, ID> repository;

    @Autowired
    private ImageDownloadService imageDownloadService;

    private final Class<T> entityClass;
    private final String apiUrl;

    public GenericApiService(Class<T> entityClass, String apiUrl) {
        this.entityClass = entityClass;
        this.apiUrl = apiUrl;
    }

    public T fetchEntityById(ID id) {
        String url = "https://digi-api.com/api/v1/" + apiUrl + id;
        String response = restTemplate.getForObject(url, String.class);

        if (response != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                T entity = objectMapper.readValue(response, entityClass);

                // Traitement spécifique si l'entité est de type Digimon
                if (entity instanceof Digimon) {
                    Digimon digimon = (Digimon) entity;

                    if (digimon.getPriorEvolutions() != null) {
                        System.out.println("Prior evolutions: " + digimon.getPriorEvolutions());
                        Set<PriorEvolution> priorEvolutionsSet = new HashSet<>();
                        for (PriorEvolution priorEvolution : digimon.getPriorEvolutions()) {
                            // Ne conserver que les champs nécessaires
                            PriorEvolution formattedPriorEvolution = new PriorEvolution();
                            formattedPriorEvolution.setDigimonPriorEvolutionId(priorEvolution.getId()); // ID de l'évolution précédente
                            formattedPriorEvolution.setCondition(priorEvolution.getCondition()); // Condition de l'évolution

                            // Associer l'évolution au Digimon
                            formattedPriorEvolution.setDigimon(digimon);

                            // Ajouter à l'ensemble des évolutions
                            priorEvolutionsSet.add(formattedPriorEvolution);
                        }
                        digimon.setPriorEvolutions(priorEvolutionsSet);
                    }

                    if (digimon.getNextEvolutions() != null) {
                        System.out.println("Next evolutions: " + digimon.getNextEvolutions());
                        Set<NextEvolution> nextEvolutionsSet = new HashSet<>();
                        for (NextEvolution nextEvolution : digimon.getNextEvolutions()) {
                            // Ne conserver que les champs nécessaires
                            NextEvolution formattedNextEvolution = new NextEvolution();
                            formattedNextEvolution.setDigimonNextEvolutionId(nextEvolution.getId()); // ID de l'évolution suivante
                            formattedNextEvolution.setCondition(nextEvolution.getCondition()); // Condition de l'évolution

                            // Associer l'évolution au Digimon
                            formattedNextEvolution.setDigimon(digimon);

                            // Ajouter à l'ensemble des évolutions
                            nextEvolutionsSet.add(formattedNextEvolution);
                        }
                        digimon.setNextEvolutions(nextEvolutionsSet);
                    }

                    // Gérer les descriptions
                    if (digimon.getDescriptions() != null) {
                        for (Description desc : digimon.getDescriptions()) {
                            desc.setDigimon(digimon); // Associer la description à l'entité Digimon
                        }
                    }

                    // Gérer les images
                    if (digimon.getImages() != null) {
                        for (Image image : digimon.getImages()) {
                            try {
                                    String localImagePath = imageDownloadService.downloadImage(image.getImage());
                                    image.setImage(localImagePath);


                            } catch (IOException e) {
                                e.printStackTrace();  // Gérer les erreurs liées au téléchargement
                            }
                            image.setDigimon(digimon); // Associer l'image à l'entité Digimon
                        }
                    }

                    // Gérer les compétences
                    if (digimon.getSkills() != null) {
                        for (Skill skill : digimon.getSkills()) {
                            skill.setDigimon(digimon); // Associer la compétence à l'entité Digimon
                        }
                    }

                }

                return entity;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public T saveEntityFromApi(ID id) {
        T entity = fetchEntityById(id);
        if (entity != null) {
            return repository.save(entity);
        }
        return null;
    }
}
