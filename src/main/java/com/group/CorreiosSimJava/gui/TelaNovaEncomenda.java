package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.*;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.FreteService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;

public class TelaNovaEncomenda extends JDialog {
    private final FreteService freteService;
    private final Cliente cliente;
    private final Frete freteEmCriacao;
    private final DefaultTableModel modeloPacotes;

    public TelaNovaEncomenda(JPanel parent, FreteService freteService, Cliente cliente) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Criar Novo Frete", true);
        this.freteService = freteService;
        this.cliente = cliente;
        this.freteEmCriacao = new Frete();
        this.freteEmCriacao.setCliente(cliente);

        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        // Painel de Entrada de Pacotes
        JPanel painelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
        JTextField txtNome = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtC = new JTextField();
        JTextField txtL = new JTextField();
        JTextField txtP = new JTextField();
        JButton btnAdd = new JButton("Adicionar Pacote");

        painelCampos.add(new JLabel("Nome do Pacote:")); painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Peso (kg):")); painelCampos.add(txtPeso);
        painelCampos.add(new JLabel("Comp (cm):")); painelCampos.add(txtC);
        painelCampos.add(new JLabel("Larg (cm):")); painelCampos.add(txtL);
        painelCampos.add(new JLabel("Prof (cm):")); painelCampos.add(txtP);
        painelCampos.add(btnAdd);

        // Tabela de Pacotes Atuais
        modeloPacotes = new DefaultTableModel(new String[]{"Nome", "Peso", "C", "L", "P"}, 0);
        JTable tabela = new JTable(modeloPacotes);

        btnAdd.addActionListener(e -> {
            Pacote p = new Pacote();
            p.setNome(txtNome.getText());
            p.setPeso(Double.parseDouble(txtPeso.getText()));
            p.setComprimento(Double.parseDouble(txtC.getText()));
            p.setLargura(Double.parseDouble(txtL.getText()));
            p.setProfundidade(Double.parseDouble(txtP.getText()));
            p.setFrete(freteEmCriacao);

            freteEmCriacao.getListaPacotes().add(p);
            modeloPacotes.addRow(new Object[]{p.getNome(), p.getPeso(), p.getComprimento(), p.getLargura(), p.getProfundidade()});
        });

        // Rodapé de Finalização
        JButton btnFinalizar = new JButton("Finalizar Frete e Salvar");
        btnFinalizar.addActionListener(e -> {
            freteEmCriacao.setOrigem((Estado) JOptionPane.showInputDialog(this, "Origem:", "Origem", JOptionPane.QUESTION_MESSAGE, null, Estado.values(), Estado.values()[0]));
            freteEmCriacao.setDestino((Estado) JOptionPane.showInputDialog(this, "Destino:", "Destino", JOptionPane.QUESTION_MESSAGE, null, Estado.values(), Estado.values()[0]));
            freteEmCriacao.setData(Instant.now());
            freteEmCriacao.calcularTotal();
            freteService.salvar(freteEmCriacao);
            dispose();
        });

        add(painelCampos, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(btnFinalizar, BorderLayout.SOUTH);
    }
}