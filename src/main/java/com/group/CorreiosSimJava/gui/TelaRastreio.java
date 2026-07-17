package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Frete;
import javax.swing.*;
import java.awt.*;
import org.springframework.stereotype.Component;

@Component
public class TelaRastreio extends JFrame {

    public TelaRastreio(Frete frete) {
        setTitle("Rastreamento - Pacote #" + frete.getId());
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 5, 5));

        // Dados da Encomenda
        JLabel lblStatus = new JLabel("Status: " + frete.getEntregaStatus().name(), SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblOrigem = new JLabel("Origem: " + frete.getOrigem().name(), SwingConstants.CENTER);
        JLabel lblDestino = new JLabel("Destino: " + frete.getDestino().name(), SwingConstants.CENTER);

        JButton btnFechar = new JButton("Voltar");
        btnFechar.addActionListener(e -> dispose());

        add(lblStatus);
        add(lblOrigem);
        add(lblDestino);
        add(btnFechar);
    }
}