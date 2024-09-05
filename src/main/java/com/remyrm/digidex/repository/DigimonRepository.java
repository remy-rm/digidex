package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonRepository extends JpaRepository<Digimon, Long> {
}

