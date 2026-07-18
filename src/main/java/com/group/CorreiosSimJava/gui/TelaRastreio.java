package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Frete;
import javax.swing.*;
import java.awt.*;

public class TelaRastreio extends JDialog {

    public TelaRastreio(Frete frete) {
        // 'true' faz com que a janela seja modal (bloqueia o pai enquanto aberta)
        super((Frame) null, "Rastreamento - Pacote #" + frete.getId(), true);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Dados da Encomenda
        JLabel lblStatus = new JLabel("Status: " + frete.getEntregaStatus().name(), SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblOrigem = new JLabel("Origem: " + (frete.getOrigem() != null ? frete.getOrigem().name() : "-"), SwingConstants.CENTER);
        JLabel lblDestino = new JLabel("Destino: " + (frete.getDestino() != null ? frete.getDestino().name() : "-"), SwingConstants.CENTER);
        JLabel lblTotal = new JLabel("Total: R$ " + String.format("%.2f", frete.getTotal()), SwingConstants.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        add(lblStatus);
        add(lblOrigem);
        add(lblDestino);
        add(lblTotal);
        add(btnFechar);
    }
}