package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.service.FreteService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TelaAdminDashboard extends JFrame {

    private FreteService freteService;
    private DefaultTableModel modeloTabela;
    private JTable tabelaFretes;

    public TelaAdminDashboard(FreteService freteService) {
        this.freteService = freteService;

        setTitle("Painel do Administrador - Correios Sim System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Tabela de Exibição ---
        String[] colunas = { "ID Pacote", "Origem", "Destino", "Status" };
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaFretes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaFretes);
        add(scrollPane, BorderLayout.CENTER);

        // --- Painel de Botões Inferiores ---
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnAtualizarLista = new JButton("Atualizar Lista");
        JButton btnNovaEncomenda = new JButton("Nova Encomenda");
        JButton btnAlterarStatus = new JButton("Alterar Status Selecionado");

        btnNovaEncomenda.addActionListener(e -> {
            TelaNovaEncomenda telaNova = new TelaNovaEncomenda(TelaAdminDashboard.this, freteService);
            telaNova.setVisible(true);
            carregarDadosTabela();
        });

        btnAlterarStatus.addActionListener(e -> {
            int linhaSelecionada = tabelaFretes.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(TelaAdminDashboard.this,
                        "Por favor, selecione uma encomenda na tabela primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Pega o ID da encomenda da primeira coluna da tabela
            Long idFrete = (Long) modeloTabela.getValueAt(linhaSelecionada, 0);

            // Puxa os status do Enum EntregaStatus
            com.group.CorreiosSimJava.entities.EntregaStatus[] statusDisponiveis = com.group.CorreiosSimJava.entities.EntregaStatus
                    .values();

            com.group.CorreiosSimJava.entities.EntregaStatus novoStatus = (com.group.CorreiosSimJava.entities.EntregaStatus) JOptionPane
                    .showInputDialog(
                            TelaAdminDashboard.this,
                            "Selecione o novo status para o pacote #" + idFrete + ":",
                            "Atualizar Status",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            statusDisponiveis,
                            statusDisponiveis[0]);

            if (novoStatus != null) {
                // TODO: Frete f = freteService.buscarPorId(idFrete);
                // f.setEntregaStatus(novoStatus.getCodigo()); freteService.salvar(f);
                JOptionPane.showMessageDialog(TelaAdminDashboard.this,
                        "Status alterado visualmente para: " + novoStatus.name());
                carregarDadosTabela();
            }
        });

        painelBotoes.add(btnAtualizarLista);
        painelBotoes.add(btnNovaEncomenda);
        painelBotoes.add(btnAlterarStatus);

        add(painelBotoes, BorderLayout.SOUTH);

        // Carrega os dados do banco para a tabela ao abrir a janela
        carregarDadosTabela();

        // Ação para recarregar a lista manualmente
        btnAtualizarLista.addActionListener(e -> carregarDadosTabela());
    }

    private void carregarDadosTabela() {
        modeloTabela.setRowCount(0);
        List<Frete> fretes = freteService.buscarTodos();

        for (Frete f : fretes) {
            Object[] linha = {
                    f.getId(),
                    f.getOrigem().name(),
                    f.getDestino().name(),
                    f.getEntregaStatus().name()
            };
            modeloTabela.addRow(linha);
        }
    }
}