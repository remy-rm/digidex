package com.remyrm.digidex.service.genericService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remyrm.digidex.common.HasImage;
import com.remyrm.digidex.entity.Description;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.entity.Image;
import com.remyrm.digidex.entity.Skill;
import com.remyrm.digidex.service.ImageDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;

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
                                // Vérifier si l'image existe déjà en local avant de la télécharger
                                if (!imageDownloadService.imageExists(image.getImage())) {
                                    // Télécharger l'image en utilisant son URL et obtenir le chemin local
                                    String localImagePath = imageDownloadService.downloadImage(image.getImage());

                                    // Assigner le chemin local de l'image à l'entité Image
                                    image.setImage(localImagePath);
                                } else {
                                    // Si l'image existe déjà, ne pas la télécharger et garder le chemin existant
                                    System.out.println("Image déjà existante, pas de téléchargement nécessaire.");
                                }

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
