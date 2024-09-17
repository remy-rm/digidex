package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    Set<Field> findByNameContaining(String name);

}