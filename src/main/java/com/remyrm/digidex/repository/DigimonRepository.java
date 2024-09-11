package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Digimon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {

    Page<Digimon> findByIdGreaterThan(Long id, Pageable pageable);
}

