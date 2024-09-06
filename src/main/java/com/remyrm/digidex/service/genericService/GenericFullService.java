package com.remyrm.digidex.service.genericService;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public abstract class GenericFullService<T, ID extends Serializable> extends GenericApiService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public GenericFullService(Class<T> entityClass, String apiUrl, JpaRepository<T, ID> repository) {
        super(entityClass, apiUrl);
        this.repository = repository;
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }
}
