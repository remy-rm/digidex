package com.remyrm.digidex.repository;

import com.remyrm.digidex.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Set<Type> findByTypeContaining(String name);
}
