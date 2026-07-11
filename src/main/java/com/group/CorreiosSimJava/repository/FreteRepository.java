package com.group.CorreiosSimJava.repository;

import com.group.CorreiosSimJava.entities.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
}
