package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Type;
import com.remyrm.digidex.repository.TypeRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TypeService extends GenericFullService<Type, Long> {

    public TypeService(TypeRepository typeRepository,
                       RestTemplate restTemplate,
                       ImageDownloadService imageDownloadService) {
        super(Type.class, "type/", typeRepository, restTemplate, imageDownloadService);
    }
}
