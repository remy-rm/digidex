package com.remyrm.digidex.controller;

import com.remyrm.digidex.entity.Field;
import com.remyrm.digidex.repository.FieldRepository;
import com.remyrm.digidex.service.FieldApiService;
import com.remyrm.digidex.service.GenericApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/field")
public class FieldController {

    private final FieldRepository fieldRepository;
    private final GenericApiService<Field, Long> fieldService; // Utilisation du service générique

    public FieldController(FieldRepository fieldRepository, FieldApiService fieldService) {
        this.fieldRepository = fieldRepository;
        this.fieldService = fieldService; // Injection du service spécifique
    }

  @GetMapping
  public ResponseEntity<List<Field>> getAllField() {
    List<Field> field = fieldRepository.findAll();
    return new ResponseEntity<>(field, HttpStatus.OK);
  }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id) {
        Optional<Field> fieldOptional = fieldRepository.findById(id);
        if (fieldOptional.isPresent()) {
            return new ResponseEntity<>(fieldOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveField(@RequestParam long id) {
        fieldService.saveEntityFromApi(id); // Appel à la méthode générique du service
        return ResponseEntity.ok("Field saved successfully");
    }


}
