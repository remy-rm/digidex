package com.remyrm.digidex.service.digimonService;

import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigimonPaginationService {

    private final DigimonRepository digimonRepository;

    public DigimonPaginationService(DigimonRepository digimonRepository) {
        this.digimonRepository = digimonRepository;
    }

    public List<Digimon> findAllByCursor(Long cursor, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("id").ascending());
        Page<Digimon> digimonPage;
        if (cursor != null) {
            digimonPage = digimonRepository.findByIdGreaterThan(cursor, pageable);
        } else {
            digimonPage = digimonRepository.findAll(pageable);
        }
        return digimonPage.getContent();
    }
}
