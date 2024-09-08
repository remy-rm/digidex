package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Evolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvolutionRepository extends JpaRepository<Evolution, Long> {

}
