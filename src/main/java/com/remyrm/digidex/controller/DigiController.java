package com.remyrm.digidex.controller;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/digimon") // Base path for all DigiMon-related API endpoints
public class DigiController {

    @Autowired
    private DigiRepository digiRepository;

    // Get all Digimon
    @GetMapping
    public ResponseEntity<List<Digimon>> getAllDigimon() {
        List<Digimon> digimon = digiRepository.findAll();
        return new ResponseEntity<>(digimon, HttpStatus.OK);
    }

    // Get a specific Digimon by ID
    @GetMapping("/{id}")
    public ResponseEntity<Digimon> getDigiMonById(@PathVariable Long id) {
        Optional<Digimon> digiMonOptional = digiRepository.findById(id);
        if (digiMonOptional.isPresent()) {
            return new ResponseEntity<>(digiMonOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Update an existing Digimon
}