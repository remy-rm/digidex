package com.remyrm.digidex.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.service.DigimonService;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digimon")
public class DigimonController {

    private final DigimonService digimonService;

    public DigimonController(DigimonService digimonService) {
        this.digimonService = digimonService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<String> createDigimon(@PathVariable Long id) {
        System.out.println("Création du Digimon avec l'id : " + id);
        Digimon digimon = digimonService.saveEntityFromApi(id);
        if (digimon != null) {
            return ResponseEntity.ok("Digimon créé avec succès");
        } else {
            return ResponseEntity.status(404).body("Digimon non trouvé");
        }
    }
}
