package com.remyrm.digidex.service;

import com.remyrm.digidex.dto.DigimonDTO;
import com.remyrm.digidex.entity.Digimon;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.digimonService.DigimonFetchService;
import com.remyrm.digidex.service.digimonService.DigimonPaginationService;
import com.remyrm.digidex.service.digimonService.GetAllWithNameContainingService;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DigimonService extends GenericFullService<Digimon, Long> {

    private final SearchDigimonByCriteriaService searchDigimonByCriteria;
    private final DigimonFetchService digimonFetchService;
    private final DigimonPaginationService digimonPaginationService;
    private final GetAllWithNameContainingService getAllWithNameContainingService;

    public DigimonService(DigimonRepository digimonRepository,
                          SearchDigimonByCriteriaService searchDigimonByCriteria,
                          ImageDownloadService imageDownloadService,
                          RestTemplate restTemplate,
                          DigimonFetchService digimonFetchService,
                          DigimonPaginationService digimonPaginationService,
                          GetAllWithNameContainingService getAllWithNameContainingService) {
        super(Digimon.class, "digimon/", digimonRepository, restTemplate, imageDownloadService);
        this.searchDigimonByCriteria = searchDigimonByCriteria;
        this.digimonFetchService = digimonFetchService;
        this.digimonPaginationService = digimonPaginationService;
        this.getAllWithNameContainingService = getAllWithNameContainingService;
    }

    public List<DigimonDTO> searchDigimon(String query, String levelNames, String typeNames, String attributeNames, String fieldNames, Long cursor, int size) {
        return searchDigimonByCriteria.searchDigimonByCriteria(query, levelNames, typeNames, attributeNames, fieldNames, cursor, size);
    }

    public Digimon fetchEntityById(Long id) {
        return digimonFetchService.fetchEntityById(id);
    }


    public List<Digimon> findAllByCursor(Long cursor, int size) {
        return digimonPaginationService.findAllByCursor(cursor, size);
    }

    public Map<String, Object> getAllWithNameContaining(String query) {
        return getAllWithNameContainingService.getAllWithNameContaining(query);
    }


}