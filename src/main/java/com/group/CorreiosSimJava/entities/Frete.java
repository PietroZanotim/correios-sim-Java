package com.group.CorreiosSimJava.entities;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Frete {

    private int id;
    private Usuario usuario;
    private List<Produto> listaProdutos;
    private Estado origem;
    private Estado destino;
    private Double total;

    public Frete(int id, Usuario usuario, Estado origem, Estado destino) {
        this.id = id;
        this.usuario = usuario;
        this.origem = origem;
        this.destino = destino;
    }


}
