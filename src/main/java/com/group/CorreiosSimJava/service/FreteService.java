package com.group.CorreiosSimJava.service;

import com.group.CorreiosSimJava.entities.EntregaStatus;
import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.repository.FreteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    // Rastreamento de encomenda pelo Cliente
    @Transactional
    public Frete buscarPorId(Long id) {
        Frete f = freteRepository.findById(id).orElse(null);
        if (f != null) {
            // Isso força o Hibernate a carregar os pacotes antes de fechar a transação
            f.getListaPacotes().size();
        }
        return f;
    }

    // Função admin: Listar TUDO
    public List<Frete> buscarTodos() {
        return freteRepository.findAll();
    }

    // Função admin: Criar/atualizar status
    @Transactional
    public Frete salvar(Frete frete) {

        // SÓ define status inicial se for um frete novo E não tiver status ainda
        if (frete.getId() == null && frete.getEntregaStatus() == null) {
            frete.setEntregaStatus(EntregaStatus.PENDENTE); // Use o Enum, não o número
        }

        return freteRepository.save(frete);
    }
}