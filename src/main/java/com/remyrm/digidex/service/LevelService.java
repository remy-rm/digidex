package com.remyrm.digidex.service;
import com.remyrm.digidex.entity.Level;
import com.remyrm.digidex.service.genericService.GenericFullService;
import com.remyrm.digidex.repository.LevelRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService extends GenericFullService<Level, Long> {

    public LevelService(LevelRepository levelRepository) {
        super(Level.class, "level/", levelRepository);
    }
}
