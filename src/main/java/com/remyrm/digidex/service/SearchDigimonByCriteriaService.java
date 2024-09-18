package com.remyrm.digidex.service;

import com.remyrm.digidex.dto.DigimonDTO;
import com.remyrm.digidex.entity.*;
import com.remyrm.digidex.repository.DigimonRepository;
import com.remyrm.digidex.service.Mapper.DigimonMapper;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchDigimonByCriteriaService {

    private final DigimonRepository digimonRepository;
    private final DigimonMapper digimonMapper;

    public SearchDigimonByCriteriaService(DigimonRepository digimonRepository, DigimonMapper digimonMapper) {
        this.digimonRepository = digimonRepository;
        this.digimonMapper = digimonMapper;
    }

    public List<DigimonDTO> searchDigimonByCriteria(
            String query,
            String levelNames,
            String typeNames,
            String attributeNames,
            String fieldNames,
            Long cursor,
            int size
    ) {
        List<Digimon> digimons = spesification(
                query, levelNames, typeNames, attributeNames, fieldNames, cursor, size
        );
        return digimons.stream()
                .map(digimonMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<Digimon> spesification(
            String query,
            String levelNames,
            String typeNames,
            String attributeNames,
            String fieldNames,
            Long cursor,
            int size
    ) {
        Specification<Digimon> spec = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (query != null && !query.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.toLowerCase() + "%"));
            }

            if (levelNames != null && !levelNames.isEmpty()) {
                List<String> levels = Arrays.stream(levelNames.split(","))
                        .map(String::trim) // Nettoyer les espaces
                        .collect(Collectors.toList());
                Join<Digimon, Level> levelsJoin = root.join("levels");
                predicate = criteriaBuilder.and(predicate, levelsJoin.get("level").in(levels));
            }

            if (typeNames != null && !typeNames.isEmpty()) {
                List<String> types = Arrays.stream(typeNames.split(","))
                        .map(String::trim) // Nettoyer les espaces
                        .collect(Collectors.toList());
                Join<Digimon, Type> typesJoin = root.join("types");
                predicate = criteriaBuilder.and(predicate, typesJoin.get("type").in(types));
            }

            if (attributeNames != null && !attributeNames.isEmpty()) {
                List<String> attributes = Arrays.stream(attributeNames.split(","))
                        .map(String::trim) // Nettoyer les espaces
                        .collect(Collectors.toList());
                Join<Digimon, Attribute> attributesJoin = root.join("attributes");
                predicate = criteriaBuilder.and(predicate, attributesJoin.get("attribute").in(attributes));
            }

            if (fieldNames != null && !fieldNames.isEmpty()) {
                List<String> fields = Arrays.stream(fieldNames.split(","))
                        .map(String::trim) // Nettoyer les espaces
                        .collect(Collectors.toList());
                Join<Digimon, Field> fieldsJoin = root.join("fields");
                predicate = criteriaBuilder.and(predicate, fieldsJoin.get("name").in(fields));
            }


            if (cursor != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThan(root.get("id"), cursor));
            }

            criteriaQuery.where(predicate);
            criteriaQuery.distinct(true); // Éviter les doublons si les jointures sont nombreuses

            return criteriaQuery.getRestriction();
        };

        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Order.asc("id"))); // Tri par ID dans l'ordre croissant
        return digimonRepository.findAll(spec, pageable).getContent();
    }


}
