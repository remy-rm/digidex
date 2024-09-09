package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.NextEvolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NexEvolutionRepository extends JpaRepository<NextEvolution, Long> {

}
