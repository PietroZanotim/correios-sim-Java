package com.group.CorreiosSimJava.entities.UsuarioImpl;

public class Cliente extends com.group.CorreiosSimJava.entities.Usuario {

    private Double saldo;
    private String cpf;
    private String numero;
    private String email;

    public Cliente(Long id, String nome, String senha, String cpf, String numero, String email, Double saldo) {
        super(id, nome, senha);
        this.cpf = cpf;
        this.numero = numero;
        this.email = email;
        this.saldo = saldo;
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
}
