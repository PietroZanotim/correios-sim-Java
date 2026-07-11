package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.exceptions.DatabaseInvalidException;
import com.group.CorreiosSimJava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void saveUser(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente findByName(String nome) {
        Cliente obj = clienteRepository.findByNome(nome);
        if(obj==null) throw new DatabaseInvalidException("Usuário inexistente");
        else return obj;
    }


}
