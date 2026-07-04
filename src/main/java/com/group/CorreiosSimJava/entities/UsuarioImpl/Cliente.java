package com.group.CorreiosSimJava.entities.UsuarioImpl;

public class Cliente extends com.group.CorreiosSimJava.entities.Usuario {

    private Double saldo;

    public Cliente(Long id, String nome, String numero, String cpf, String email, Double saldo) {
        super(id, nome, numero, cpf, email);
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
