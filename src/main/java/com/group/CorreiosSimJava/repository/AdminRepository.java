package com.group.CorreiosSimJava.repository;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
