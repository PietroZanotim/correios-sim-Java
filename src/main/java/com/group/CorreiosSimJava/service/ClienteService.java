package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.repository.AdminRepository;
import com.group.CorreiosSimJava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void salvarUsuario(Cliente cliente) {
        clienteRepository.save(cliente);
    }

}
