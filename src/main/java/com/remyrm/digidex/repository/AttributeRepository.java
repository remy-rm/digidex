package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    Set<Attribute> findByAttributeContaining(String name);
}
