package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    // Rastreamento de encomenda pelo Cliente
    public Frete buscarPorId(Long id) {
        Optional<Frete> obj = freteRepository.findById(id);
        return obj.orElse(null);
    }

    // Função admin: Listar TUDO
    public List<Frete> buscarTodos() {
        return freteRepository.findAll();
    }

    // Função admin: Criar/atualizar status
    public void salvar(Frete frete) {
        freteRepository.save(frete);
    }
}