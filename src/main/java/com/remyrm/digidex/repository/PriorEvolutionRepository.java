package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.PriorEvolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorEvolutionRepository extends JpaRepository<PriorEvolution, Long> {
}
