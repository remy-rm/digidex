package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Digimon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long>, JpaSpecificationExecutor<Digimon> {

    @Query("SELECT d FROM Digimon d WHERE d.name LIKE %:query%")
    Set<Digimon> findByCriteria(@Param("query") String query);

    Page<Digimon> findByIdGreaterThan(Long id, Pageable pageable);

}

