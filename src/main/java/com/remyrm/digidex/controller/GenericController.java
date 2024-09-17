package com.remyrm.digidex.controller;

import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericController<T, ID extends Serializable> {

    protected final GenericFullService<T, ID> service;

    protected GenericController(GenericFullService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAllEntities() {
        return ResponseEntity.ok((List<T>) service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getEntityById(@PathVariable ID id) {
        Optional<T> entityOptional = service.findById(id);
        return entityOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveEntity(@RequestParam ID id) {
        service.saveEntityFromApi(id);
        return ResponseEntity.ok("Entity saved successfully");
    }

}
