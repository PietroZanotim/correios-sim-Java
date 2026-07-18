package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.exceptions.DatabaseInvalidException;
import com.group.CorreiosSimJava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> autenticar(String nome, String senha) {
        return clienteRepository.findByNomeAndSenha(nome, senha);
    }

    public Cliente salvarCliente(Cliente cliente) throws Exception {
        // Regra de negócio: não permite cadastrar nome que contenha "admin"
        if (cliente.getNome() != null && cliente.getNome().toLowerCase().contains("admin")) {
            throw new Exception("Nome de usuário reservado ao sistema!");
        }

        // Verifica se o nome exato já existe no banco
        if (clienteRepository.existsByNomeIgnoreCase(cliente.getNome())) {
            throw new Exception("Nome de usuário já está em uso!");
        }

        return clienteRepository.save(cliente);
    }


}
