package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Level;
import com.remyrm.digidex.repository.LevelRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LevelService extends GenericFullService<Level, Long> {

    public LevelService(LevelRepository levelRepository,
                        RestTemplate restTemplate,
                        ImageDownloadService imageDownloadService) {
        super(Level.class, "level/", levelRepository, restTemplate, imageDownloadService);
    }
}
