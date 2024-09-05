package com.remyrm.digidex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final Class<T> entityClass;
    private final String apiUrl;

    public GenericApiService(Class<T> entityClass, String apiUrl) {
        this.entityClass = entityClass;
        this.apiUrl = apiUrl;
    }

    public T fetchEntityById(ID id) {
        String url = apiUrl + id;
        String response = restTemplate.getForObject(url, String.class);

        if (response != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response, entityClass);
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
