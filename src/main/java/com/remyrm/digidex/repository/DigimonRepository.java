package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Digimon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long >, JpaSpecificationExecutor<Digimon> {

    Page<Digimon> findByIdGreaterThan(Long id, Pageable pageable);

}

