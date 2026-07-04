package com.group.CorreiosSimJava.entities.UsuarioImpl;

public class Usuario extends com.group.CorreiosSimJava.entities.Usuario {

    private Double saldo;

    public Usuario(String nome, String numero, String cpf, String email, Double saldo) {
        super(nome, numero, cpf, email);
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
