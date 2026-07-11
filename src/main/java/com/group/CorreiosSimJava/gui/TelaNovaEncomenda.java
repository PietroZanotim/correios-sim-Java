package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Estado;
import com.group.CorreiosSimJava.service.FreteService;
import javax.swing.*;
import java.awt.*;

public class TelaNovaEncomenda extends JDialog {

    public TelaNovaEncomenda(JFrame parent, FreteService freteService) {
        super(parent, "Cadastrar Nova Encomenda", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(7, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Puxar os valores do Enum
        JComboBox<Estado> cbOrigem = new JComboBox<>(Estado.values());
        JComboBox<Estado> cbDestino = new JComboBox<>(Estado.values());

        JTextField txtPeso = new JTextField();
        JTextField txtComprimento = new JTextField();
        JTextField txtLargura = new JTextField();
        JTextField txtProfundidade = new JTextField();

        form.add(new JLabel("Estado de Origem:"));
        form.add(cbOrigem);
        form.add(new JLabel("Estado de Destino:"));
        form.add(cbDestino);
        form.add(new JLabel("Peso (kg):"));
        form.add(txtPeso);
        form.add(new JLabel("Comprimento:"));
        form.add(txtComprimento);
        form.add(new JLabel("Largura:"));
        form.add(txtLargura);
        form.add(new JLabel("Profundidade:"));
        form.add(txtProfundidade);

        add(form, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar Encomenda");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> dispose());

        btnSalvar.addActionListener(e -> {
            // TODO: Usar os dados e instanciar Frete e Pacote, vincular ao Cliente e salvar
            // via freteService
            JOptionPane.showMessageDialog(this, "Nova encomenda registrada visualmente!");
            dispose();
        });

        botoes.add(btnSalvar);
        botoes.add(btnCancelar);
        add(botoes, BorderLayout.SOUTH);
    }
}