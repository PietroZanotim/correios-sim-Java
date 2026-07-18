package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Estado;
import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.FreteService;
import javax.swing.*;
import java.awt.*;
import java.time.Instant;

public class TelaNovaEncomenda extends JDialog {

    private final FreteService freteService;
    private final Cliente cliente;

    public TelaNovaEncomenda(JPanel parent, FreteService freteService, Cliente cliente) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Nova Encomenda", true);
        this.freteService = freteService;
        this.cliente = cliente;

        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<Estado> cbOrigem = new JComboBox<>(Estado.values());
        JComboBox<Estado> cbDestino = new JComboBox<>(Estado.values());
        JTextField txtPeso = new JTextField();
        JTextField txtComprimento = new JTextField();
        JTextField txtLargura = new JTextField();
        JTextField txtProfundidade = new JTextField();

        form.add(new JLabel("Origem:")); form.add(cbOrigem);
        form.add(new JLabel("Destino:")); form.add(cbDestino);
        form.add(new JLabel("Peso (kg):")); form.add(txtPeso);
        form.add(new JLabel("Comprimento (cm):")); form.add(txtComprimento);
        form.add(new JLabel("Largura (cm):")); form.add(txtLargura);
        form.add(new JLabel("Profundidade (cm):")); form.add(txtProfundidade);

        add(form, BorderLayout.CENTER);

        JButton btnSalvar = new JButton("Salvar Encomenda");
        btnSalvar.addActionListener(e -> salvar(
                (Estado) cbOrigem.getSelectedItem(),
                (Estado) cbDestino.getSelectedItem(),
                txtPeso.getText(),
                txtComprimento.getText(),
                txtLargura.getText(),
                txtProfundidade.getText()
        ));

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        add(botoes, BorderLayout.SOUTH);
    }

    private void salvar(Estado origem, Estado destino, String peso, String comp, String larg, String prof) {
        try {
            Frete frete = new Frete();
            frete.setCliente(this.cliente);
            frete.setOrigem(origem);
            frete.setDestino(destino);
            frete.setData(Instant.now());
            // Aqui a lógica chama o cálculo total que definimos antes
            frete.calcularTotal();

            freteService.salvar(frete);
            JOptionPane.showMessageDialog(this, "Frete criado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}