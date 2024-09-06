package com.remyrm.digidex.controller;
import com.remyrm.digidex.entity.Level;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level")
public class LevelController extends GenericController<Level, Long> {

    public LevelController(GenericFullService<Level, Long> levelService) {
        super(levelService);
    }
}
