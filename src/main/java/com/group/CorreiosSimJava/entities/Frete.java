package com.group.CorreiosSimJava.entities;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Frete {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Cria a coluna da chave estrangeira no banco
    private Cliente cliente;

    // Mapeamento bidirecional clássico
    @OneToMany(mappedBy = "frete", cascade = CascadeType.ALL)
    private List<Pacote> listaPacotes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Estado origem;

    @Enumerated(EnumType.STRING)
    private Estado destino;

    private Instant data;
    private Double total=0.0;

    public Frete() {
    }

    public Frete(Long id, Cliente cliente, List<Pacote> produtos, Estado origem, Estado destino, Instant data) {
        this.id = id;
        this.cliente = cliente;
        this.listaPacotes = produtos;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
    }

    public void calcularTotal() {



    }


}
