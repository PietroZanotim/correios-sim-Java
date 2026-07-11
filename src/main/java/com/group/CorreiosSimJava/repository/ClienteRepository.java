package com.group.CorreiosSimJava.repository;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
