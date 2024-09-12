package com.remyrm.digidex.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.DigimonService;
import com.remyrm.digidex.views.Views;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/digimon")
public class DigimonController {

    private final DigimonService digimonService;
    private final DigimonRepository digimonRepository;

    public DigimonController(DigimonService digimonService, DigimonRepository digimonRepository) {
        this.digimonService = digimonService;
        this.digimonRepository = digimonRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Digimon>> getAllByCursor(
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "20") int size) {
        List<Digimon> digimons = digimonService.findAllByCursor(cursor, size);
        return ResponseEntity.ok(digimons);
    }

    @GetMapping("/{id}")
    @JsonView(Views.DigimonDetails.class)
    public ResponseEntity<Digimon> getEntityById(@PathVariable long id) {
        Optional<Digimon> entityOptional = digimonService.findById(id);
        return entityOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/fetch")
    public ResponseEntity<String> createDigimon(@RequestParam long id) {
        Digimon digimon = digimonService.saveEntityFromApi(id);
        if (digimon != null) {
            digimonService.save(digimon);
            return ResponseEntity.ok("Digimon créé avec succès");
        } else {
            return ResponseEntity.status(404).body("Digimon non trouvé");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Digimon>> searchDigimonByCriteria(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String levelNames,
            @RequestParam(required = false) String typeNames,
            @RequestParam(required = false) String attributeNames,
            @RequestParam(required = false) String fieldNames,
            @RequestParam(required = false) String skillNames,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(digimonService.searchDigimonByCriteria(
                query,
                levelNames,
                typeNames,
                attributeNames,
                fieldNames,
                cursor,
                size
        ));
    }

}




