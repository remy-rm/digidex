package com.remyrm.digidex.controller;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.service.GenericApiService;
import com.remyrm.digidex.service.DigimonApiService;
import com.remyrm.digidex.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/digimon")
public class DigiController {

    private final DigimonRepository digiRepository;
    private final GenericApiService<Digimon, Long> digimonApiService; // Utilisation du service générique

    @Autowired
    public DigiController(DigimonRepository digiRepository, DigimonApiService digimonApiService) {
        this.digiRepository = digiRepository;
        this.digimonApiService = digimonApiService;
    }

    @GetMapping
    public ResponseEntity<List<Digimon>> getAllDigimon() {
        List<Digimon> digimon = digiRepository.findAll();
        return new ResponseEntity<>(digimon, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Digimon> getDigiMonById(@PathVariable Long id) {
        Optional<Digimon> digiMonOptional = digiRepository.findById(id);
        if (digiMonOptional.isPresent()) {
            return new ResponseEntity<>(digiMonOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveDigimon(@RequestParam long id) {
        digimonApiService.saveEntityFromApi(id); // Appel à la méthode générique
        return ResponseEntity.ok("Digimon saved successfully");
    }

    // Autres méthodes pour mettre à jour les Digimon
}
