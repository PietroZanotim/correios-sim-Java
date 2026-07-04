package com.group.CorreiosSimJava.entities;

import jakarta.persistence.Entity;

@Entity
public class Produto {

    private String nome;
    private Double peso;
    private Double comprimento, largura, profundidade;

    public Produto(String nome, Double peso, Double comprimento, Double largura, Double profundidade) {
        this.nome = nome;
        this.peso = peso;
        this.comprimento = comprimento;
        this.largura = largura;
        this.profundidade = profundidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }
}
