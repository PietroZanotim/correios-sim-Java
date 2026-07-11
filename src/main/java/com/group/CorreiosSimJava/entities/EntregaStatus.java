package com.group.CorreiosSimJava.entities;

public enum EntregaStatus {

    PENDENTE(1),
    SAIU_PARA_ENTREGA(2),
    ENTREGUE(3);

    private final int status;

    EntregaStatus(int valor) {
        this.status = valor;
    }

    public int getCodigo() {
        return status;
    }

    public static EntregaStatus valueOf(int codigo) {
        for (EntregaStatus valor : EntregaStatus.values()) {
            if (valor.getCodigo() == codigo) {
                return valor;
            }
        }
        throw new IllegalArgumentException("Código de status de entrega inválido!");
    }
}
