package com.group.CorreiosSimJava.entities;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Frete {

    @Id
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

    @Enumerated(EnumType.STRING)
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

        if (listaPacotes == null || listaPacotes.isEmpty()) {
            this.total = 0.0;
            return;
        }

        double somaPesosParaCobranca = 0.0;

        // 1. Calcula o peso de cada pacote
        for (Pacote pacote : listaPacotes) {
            // Peso real do pacote
            double pesoFisico = pacote.getPeso() != null ? pacote.getPeso() : 0.0;

            // Peso cúbico = (C x L x P) / 6000 (Fator de cubagem padrão dos Correios)
            double comp = pacote.getComprimento() != null ? pacote.getComprimento() : 0.0;
            double larg = pacote.getLargura() != null ? pacote.getLargura() : 0.0;
            double prof = pacote.getProfundidade() != null ? pacote.getProfundidade() : 0.0;

            double pesoCubado = (comp * larg * prof) / 6000.0;

            // Pega o maior peso entre o físico e o cubado
            somaPesosParaCobranca += Math.max(pesoFisico, pesoCubado);
        }

        // 2. Calcula a distância (diferença entre faixas dos estados)
        int faixaOrigem = origem != null ? origem.getValor() : 1;
        int faixaDestino = destino != null ? destino.getValor() : 1;

        // Math.abs garante que o valor seja sempre positivo
        int saltosDeFaixa = Math.abs(faixaOrigem - faixaDestino);

        // 3. Aplica os preços
        double valorBasePorKg = 15.00; // Custa 15 reais por Kg
        double taxaPorSaltoDeFaixa = 12.50; // Cada faixa de distância custa 12,50 a mais

        // Se estiver na mesma faixa, cobra apenas um salto mínimo para não ficar "grátis" a distância
        if (saltosDeFaixa == 0) {
            saltosDeFaixa = 1;
        }

        // 4. Fórmula final
        this.total = (somaPesosParaCobranca * valorBasePorKg) + (saltosDeFaixa * taxaPorSaltoDeFaixa);

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

    public void setEntregaStatus(int codigo) {
        this.entregaStatus = EntregaStatus.valueOf(codigo);
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
