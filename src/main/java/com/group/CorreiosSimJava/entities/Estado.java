package com.group.CorreiosSimJava.entities;

public enum Estado {

    // Faixa 1: Extremo Norte
    RR(1),
    AP(1),

    // Faixa 2: Norte Superior e Nordeste Superior
    AM(2),
    PA(2),
    MA(2),
    CE(2),
    RN(2),

    // Faixa 3: Norte Central e Nordeste Central
    AC(3),
    RO(3),
    TO(3),
    PI(3),
    PB(3),
    PE(3),
    AL(3),
    SE(3),

    // Faixa 4: Centro e Início da Bahia
    MT(4),
    BA(4),

    // Faixa 5: Centro-Sul e Sudeste Superior
    GO(5),
    DF(5),
    MG(5),
    ES(5),

    // Faixa 6: Sudeste Inferior e MS
    MS(6),
    SP(6),
    RJ(6),

    // Faixa 7, 8 e 9: Sul
    PR(7),
    SC(8),
    RS(9);

    private final int valor;

    Estado(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
