package com.group.CorreiosSimJava.repository;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // O Spring cria o SQL: SELECT * FROM tb_cliente WHERE nome = ? AND senha = ?
    Optional<Cliente> findByNomeAndSenha(String nome, String senha);

    // Metodo para verificar se o nome de usuário já existe
    boolean existsByNomeIgnoreCase(String nome);
}
