package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.AdminService;
import com.group.CorreiosSimJava.service.ClienteService;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

// Isso agora é um JPanel, não um JFrame. Ele mora DENTRO da JanelaPrincipal
public class PainelLogin extends JPanel {

    private final JanelaPrincipal janelaPrincipal;
    private final AdminService adminService;
    private final ClienteService clienteService;

    private JTextField txtNome;
    private JPasswordField txtSenha;

    public PainelLogin(JanelaPrincipal janelaPrincipal, AdminService adminService, ClienteService clienteService) {
        this.janelaPrincipal = janelaPrincipal;
        this.adminService = adminService;
        this.clienteService = clienteService;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Componentes ---
        JLabel lblTitulo = new JLabel("Acesso ao Sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Nome de Usuário:"), gbc);

        txtNome = new JTextField(20);
        gbc.gridx = 1;
        add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Senha:"), gbc);

        txtSenha = new JPasswordField(20);
        gbc.gridx = 1;
        add(txtSenha, gbc);

        JButton btnEntrar = new JButton("Entrar");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnEntrar, gbc);

        JButton btnNovoCliente = new JButton("Criar Conta Cliente");
        gbc.gridy = 4;
        add(btnNovoCliente, gbc);

        // --- Ações ---
        btnEntrar.addActionListener(e -> tentarAutenticar());

        btnNovoCliente.addActionListener(e -> {
            TelaCadastroCliente telaCadastro = new TelaCadastroCliente(janelaPrincipal, clienteService);
            telaCadastro.setVisible(true);
        });
    }

    private void tentarAutenticar() {
        String nomeDigitado = txtNome.getText().trim();
        String senhaDigitada = new String(txtSenha.getPassword());

        if (nomeDigitado.isEmpty() || senhaDigitada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha nome e senha!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Tenta achar como Admin
            Optional<Admin> admin = adminService.autenticar(nomeDigitado, senhaDigitada);
            if (admin.isPresent()) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, Admin " + nomeDigitado + "!");
                limparCampos();
                janelaPrincipal.fazerLogin(admin.get());
                return;
            }

            // Tenta achar como Cliente
            Optional<Cliente> cliente = clienteService.autenticar(nomeDigitado, senhaDigitada);
            if (cliente.isPresent()) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, Cliente " + nomeDigitado + "!");
                limparCampos();
                janelaPrincipal.fazerLogin(cliente.get());
                return;
            }

            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtSenha.setText("");
    }
}