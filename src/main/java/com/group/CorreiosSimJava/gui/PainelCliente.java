package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.FreteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PainelCliente extends JPanel {

    private final JanelaPrincipal janelaPrincipal;
    private final FreteService freteService;
    private final Cliente clienteLogado;

    private DefaultTableModel modeloTabela;
    private JTable tabelaFretes;

    public PainelCliente(JanelaPrincipal janelaPrincipal, FreteService freteService, Cliente cliente) {
        this.janelaPrincipal = janelaPrincipal;
        this.freteService = freteService;
        this.clienteLogado = cliente;

        setLayout(new BorderLayout(10, 10));

        // --- Cabeçalho ---
        JPanel painelTopo = new JPanel(new BorderLayout());
        JLabel lblBemVindo = new JLabel(" Bem-vindo(a), " + cliente.getNome() + "!", SwingConstants.LEFT);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 16));
        painelTopo.add(lblBemVindo, BorderLayout.WEST);

        JButton btnLogout = new JButton("Sair");
        btnLogout.addActionListener(e -> janelaPrincipal.fazerLogout());
        painelTopo.add(btnLogout, BorderLayout.EAST);
        add(painelTopo, BorderLayout.NORTH);

        // --- Tabela ---
        String[] colunas = {"ID", "Origem", "Destino", "Data", "Status", "Total (R$)"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Tabela não editável
        };
        tabelaFretes = new JTable(modeloTabela);
        add(new JScrollPane(tabelaFretes), BorderLayout.CENTER);

        // --- Botões Inferiores ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnNovoFrete = new JButton("Nova Encomenda");
        JButton btnDetalhes = new JButton("Detalhes/Pacotes");
        JButton btnAtualizar = new JButton("Atualizar");

        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDetalhes);
        painelBotoes.add(btnNovoFrete);
        add(painelBotoes, BorderLayout.SOUTH);

        // --- Ações ---
        btnAtualizar.addActionListener(e -> carregarFretes());

        btnNovoFrete.addActionListener(e -> {
            TelaNovaEncomenda tela = new TelaNovaEncomenda(this, freteService, clienteLogado);
            tela.setVisible(true);
            carregarFretes(); // Atualiza a tabela após fechar
        });

        btnDetalhes.addActionListener(e -> {
            int linhaSelecionada = tabelaFretes.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um frete para ver os detalhes.");
                return;
            }
            Long idFrete = (Long) modeloTabela.getValueAt(linhaSelecionada, 0);
            Frete frete = freteService.buscarPorId(idFrete);
            TelaRastreio rastreio = new TelaRastreio(frete);
            rastreio.setVisible(true);
        });

        carregarFretes();
    }

    private void carregarFretes() {
        modeloTabela.setRowCount(0);
        try {
            // Busca todos os fretes e filtra APENAS os do cliente logado
            List<Frete> todosFretes = freteService.buscarTodos();
            if (todosFretes != null) {
                List<Frete> meusFretes = todosFretes.stream()
                        .filter(f -> f.getCliente() != null && f.getCliente().getId().equals(clienteLogado.getId()))
                        .collect(Collectors.toList());

                for (Frete f : meusFretes) {
                    Object[] linha = {
                            f.getId(),
                            f.getOrigem() != null ? f.getOrigem().name() : "-",
                            f.getDestino() != null ? f.getDestino().name() : "-",
                            f.getData() != null ? f.getData().toString() : "-",
                            f.getEntregaStatus() != null ? f.getEntregaStatus().name() : "-",
                            String.format("%.2f", f.getTotal())
                    };
                    modeloTabela.addRow(linha);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar fretes: " + e.getMessage());
        }
    }
}
