package com.remyrm.digidex.service.genericService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remyrm.digidex.common.HasImage;
import com.remyrm.digidex.service.ImageDownloadService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;

public abstract class GenericApiService<T, ID extends Serializable> {


    private final RestTemplate restTemplate;

    private final JpaRepository<T, ID> repository;

    private final ImageDownloadService imageDownloadService;

    private final Class<T> entityClass;

    private final String apiUrl;

    public GenericApiService(Class<T> entityClass, String apiUrl, JpaRepository<T, ID> repository, ImageDownloadService imageDownloadService, RestTemplate restTemplate) {
        this.entityClass = entityClass;
        this.apiUrl = apiUrl;
        this.repository = repository;
        this.imageDownloadService = imageDownloadService;
        this.restTemplate = restTemplate;

    }

    public T fetchEntityById(ID id) {
        String url = "https://digi-api.com/api/v1/" + apiUrl + id;
        String response = restTemplate.getForObject(url, String.class);

        if (response != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                T entity = objectMapper.readValue(response, entityClass);

                if (entity instanceof HasImage) {
                    String imageUrl = ((HasImage) entity).getImage();
                    String storedImagePath = imageDownloadService.downloadImage(imageUrl);
                    ((HasImage) entity).setImage(storedImagePath);
                }

                return entity;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Transactional
    public T saveEntityFromApi(ID id) {
        T entity = fetchEntityById(id);
        if (entity != null) {
            return repository.save(entity);
        }
        return null;
    }
}
