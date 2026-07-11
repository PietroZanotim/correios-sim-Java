package com.group.CorreiosSimJava.entities.UsuarioImpl;

import com.group.CorreiosSimJava.entities.Frete;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_cliente")
public class Cliente extends com.group.CorreiosSimJava.entities.Usuario {

    private Double saldo;
    private String cpf;
    private String numero;
    private String email;

    // Relacionamento bidirecional mapeado pelo atributo "usuario" lá na classe Frete
    @OneToMany(mappedBy = "cliente")
    private List<Frete> fretes = new ArrayList<>();

    public Cliente(Long id, String nome, String senha, String cpf, String numero, String email) {
        super(id, nome, senha);
        this.cpf = cpf;
        this.numero = numero;
        this.email = email;
        this.saldo = 0.0;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Frete> getFretes() {
        return fretes;
    }
}
