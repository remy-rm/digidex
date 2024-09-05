package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FieldRepository extends JpaRepository<Field, Long> {
}