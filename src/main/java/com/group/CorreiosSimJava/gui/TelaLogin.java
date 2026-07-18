package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.service.AdminService;
import com.group.CorreiosSimJava.service.ClienteService;
import com.group.CorreiosSimJava.service.FreteService;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TelaLogin extends JFrame {

    private final FreteService freteService;
    private final AdminService adminService;
    private final ClienteService clienteService;

    public TelaLogin(FreteService freteService, AdminService adminService, ClienteService clienteService) {
        this.freteService = freteService;
        this.adminService = adminService;
        this.clienteService = clienteService;

        // Configurações da janela
        setTitle("Correios Sim System - Rastreio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Área do Cliente ---
        JPanel painelCliente = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitulo = new JLabel("Rastrear Encomenda");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        painelCliente.add(lblTitulo, gbc);

        JLabel lblCodigo = new JLabel("Código (ID):");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        painelCliente.add(lblCodigo, gbc);

        JTextField txtCodigo = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        painelCliente.add(txtCodigo, gbc);

        JButton btnRastrear = new JButton("Buscar");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        painelCliente.add(btnRastrear, gbc);

        JButton btnCadastrar = new JButton("Criar Conta");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        painelCliente.add(btnCadastrar, gbc);

        // --- Área do Admin ---
        JPanel painelAdmin = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLoginAdmin = new JButton("Acesso Admin");
        painelAdmin.add(btnLoginAdmin);

        add(painelCliente, BorderLayout.CENTER);
        add(painelAdmin, BorderLayout.SOUTH);

        // --- Listeners ---

        btnRastrear.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            if (codigo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(TelaLogin.this, "Insira um código numérico.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Long idFrete = Long.parseLong(codigo.trim());
                Frete freteEncontrado = freteService.buscarPorId(idFrete);

                if (freteEncontrado != null) {
                    TelaRastreio telaRastreio = new TelaRastreio(freteEncontrado);
                    telaRastreio.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Encomenda não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Captura o erro caso o usuário digite letras ou símbolos ao invés de números
                JOptionPane.showMessageDialog(TelaLogin.this, "O código inserido é inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }); // <-- Fechamento correto do Listener do btnRastrear

        // Listeners retirados de dentro do try-catch e movidos para fora
        btnLoginAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirLoginAdmin();
            }
        });

        btnCadastrar.addActionListener(e -> {
            TelaCadastroCliente telaCadastro = new TelaCadastroCliente(TelaLogin.this, clienteService);
            telaCadastro.setVisible(true);
        });
    }

    void abrirLoginAdmin() {
        JPasswordField passwordField = new JPasswordField();
        Object[] mensagem = { "Senha do Administrador:", passwordField };

        int opcao = JOptionPane.showConfirmDialog(this, mensagem, "Login Admin", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            String senha = new String(passwordField.getPassword());
            if ("admin123".equals(senha)) {
                JOptionPane.showMessageDialog(this, "Acesso Permitido!");
                // Instancia MANUALMENTE o Dashboard passando os services necessários
                TelaAdminDashboard painel = new TelaAdminDashboard(freteService);
                painel.setVisible(true);
                dispose(); // Fecha o login
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}