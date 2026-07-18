package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.EntregaStatus;
import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import com.group.CorreiosSimJava.service.FreteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PainelAdmin extends JPanel {

    private final JanelaPrincipal janelaPrincipal;
    private final FreteService freteService;
    private final Admin adminLogado;

    private DefaultTableModel modeloTabela;
    private JTable tabelaFretes;

    public PainelAdmin(JanelaPrincipal janelaPrincipal, FreteService freteService, Admin admin) {
        this.janelaPrincipal = janelaPrincipal;
        this.freteService = freteService;
        this.adminLogado = admin;

        setLayout(new BorderLayout(10, 10));

        // --- Cabeçalho ---
        JPanel painelTopo = new JPanel(new BorderLayout());
        JLabel lblInfo = new JLabel(
                String.format(" Painel Administrativo | %s | Faixa de Controle: %d",
                        admin.getNome(), admin.getFaixaControle()), SwingConstants.LEFT);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
        painelTopo.add(lblInfo, BorderLayout.WEST);

        JButton btnLogout = new JButton("Sair");
        btnLogout.addActionListener(e -> janelaPrincipal.fazerLogout());
        painelTopo.add(btnLogout, BorderLayout.EAST);
        add(painelTopo, BorderLayout.NORTH);

        // --- Tabela ---
        String[] colunas = {"ID", "Cliente", "Origem", "Destino", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaFretes = new JTable(modeloTabela);
        add(new JScrollPane(tabelaFretes), BorderLayout.CENTER);

        // --- Botões Inferiores ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDetalhes = new JButton("Detalhes Completos");
        JButton btnAlterarStatus = new JButton("Alterar Status");
        JButton btnAtualizar = new JButton("Atualizar");

        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDetalhes);
        painelBotoes.add(btnAlterarStatus);
        add(painelBotoes, BorderLayout.SOUTH);

        // --- Ações ---
        btnAtualizar.addActionListener(e -> carregarFretesDaFaixa());

        btnDetalhes.addActionListener(e -> {
            int linha = tabelaFretes.getSelectedRow();
            if(linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma encomenda.");
                return;
            }
            Long idFrete = (Long) modeloTabela.getValueAt(linha, 0);
            Frete frete = freteService.buscarPorId(idFrete);
            TelaDetalhesFrete rastreioDetalhado = new TelaDetalhesFrete(frete);
            rastreioDetalhado.setVisible(true);
        });

        btnAlterarStatus.addActionListener(e -> alterarStatusFrete());

        carregarFretesDaFaixa();
    }

    private void alterarStatusFrete() {
        int linhaSelecionada = tabelaFretes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma encomenda na tabela.");
            return;
        }

        Long idFrete = (Long) modeloTabela.getValueAt(linhaSelecionada, 0);
        Frete frete = freteService.buscarPorId(idFrete);

        EntregaStatus[] statusDisponiveis = EntregaStatus.values();
        EntregaStatus novoStatus = (EntregaStatus) JOptionPane.showInputDialog(
                this, "Selecione o novo status para o frete #" + idFrete,
                "Atualizar Status", JOptionPane.QUESTION_MESSAGE, null,
                statusDisponiveis, frete.getEntregaStatus());

        if (novoStatus != null && novoStatus != frete.getEntregaStatus()) {
            // Atualiza usando o número (código) do Enum, como definimos na refatoração
            frete.setEntregaStatus(novoStatus);
            freteService.salvar(frete); // O Spring cuida do UPDATE
            JOptionPane.showMessageDialog(this, "Status atualizado com sucesso!");
            carregarFretesDaFaixa();
        }
    }

    private void carregarFretesDaFaixa() {
        modeloTabela.setRowCount(0);
        try {
            List<Frete> todosFretes = freteService.buscarTodos();
            if (todosFretes != null) {
                // Filtra APENAS fretes cujo estado de origem OU destino estejam na faixa do Admin
                List<Frete> fretesDaFaixa = todosFretes.stream()
                        .filter(f -> (f.getOrigem() != null && f.getOrigem().getValor() == adminLogado.getFaixaControle()) ||
                                (f.getDestino() != null && f.getDestino().getValor() == adminLogado.getFaixaControle()))
                        .collect(Collectors.toList());

                for (Frete f : fretesDaFaixa) {
                    Object[] linha = {
                            f.getId(),
                            f.getCliente() != null ? f.getCliente().getNome() : "Sem Cliente",
                            f.getOrigem() != null ? f.getOrigem().name() : "-",
                            f.getDestino() != null ? f.getDestino().name() : "-",
                            f.getEntregaStatus() != null ? f.getEntregaStatus().name() : "-"
                    };
                    modeloTabela.addRow(linha);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar fretes: " + e.getMessage());
        }
    }
}