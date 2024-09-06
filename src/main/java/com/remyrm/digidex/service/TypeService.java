package com.remyrm.digidex.service;

import com.remyrm.digidex.entity.Type;
import com.remyrm.digidex.repository.TypeRepository;
import com.remyrm.digidex.service.genericService.GenericFullService;
import org.springframework.stereotype.Service;

@Service
public class TypeService extends GenericFullService<Type, Long> {

    public TypeService(TypeRepository typeRepository) {
        super(Type.class, "type/", typeRepository);
    }
}
