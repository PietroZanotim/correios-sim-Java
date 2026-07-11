package com.group.CorreiosSimJava.entities;

public enum EntregaStatus {

    PENDENTE(1),
    SAIU_PARA_ENTREGA(2),
    ENTREGUE(3);

    private int status;

    EntregaStatus(int valor) {
        this.status = valor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
