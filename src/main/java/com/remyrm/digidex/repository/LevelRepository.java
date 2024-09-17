package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    Set<Level> findByLevelContaining(String name);
}
