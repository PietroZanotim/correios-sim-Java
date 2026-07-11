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
    private EntregaStatus entregaStatus;
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
        setEntregaStatus(3);
    }

    public void calcularTotal() {



    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado getOrigem() {
        return origem;
    }

    public void setOrigem(Estado origem) {
        this.origem = origem;
    }

    public Estado getDestino() {
        return destino;
    }

    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public EntregaStatus getEntregaStatus() {
        return entregaStatus;
    }

    public void setEntregaStatus(int valor) {
        this.entregaStatus.setStatus(valor);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Pacote> getListaPacotes() {
        return listaPacotes;
    }
}
