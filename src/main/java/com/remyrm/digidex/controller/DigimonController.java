package com.remyrm.digidex.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.remyrm.digidex.dto.DigimonDTO;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.Mapper.DigimonMapper;
import com.remyrm.digidex.service.digimonService.DigimonService;
import com.remyrm.digidex.views.Views;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/digimon")
public class DigimonController {

    private final DigimonService digimonService;
    private final DigimonMapper digimonMapper;

    public DigimonController(DigimonService digimonService, DigimonRepository digimonRepository, DigimonMapper digimonMapper) {
        this.digimonService = digimonService;
        this.digimonMapper = digimonMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DigimonDTO>> getAllByCursor(
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "20") int size) {
        List<Digimon> digimons = digimonService.findAllByCursor(cursor, size);
        List<DigimonDTO> digimonDTOs = digimons.stream()
                .map(digimonMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(digimonDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigimonDTO> getEntityById(@PathVariable long id) {
        Optional<Digimon> entityOptional = digimonService.findById(id);
        return entityOptional.map(digimonMapper::toDTO)
                .map(ResponseEntity::ok)
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
    public ResponseEntity<List<DigimonDTO>> searchDigimonByCriteria(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String levelNames,
            @RequestParam(required = false) String typeNames,
            @RequestParam(required = false) String attributeNames,
            @RequestParam(required = false) String fieldNames,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(digimonService.searchDigimon(
                query,
                levelNames,
                typeNames,
                attributeNames,
                fieldNames,
                cursor,
                size
        ));
    }

    @GetMapping("/searchAll")
    @JsonView(Views.DigimonSearchAll.class)
    public ResponseEntity<Map<String, Object>> search(@RequestParam String query) {
        Map<String, Object> result = digimonService.getAllWithNameContaining(query);
        return ResponseEntity.ok(result);
    }

}




