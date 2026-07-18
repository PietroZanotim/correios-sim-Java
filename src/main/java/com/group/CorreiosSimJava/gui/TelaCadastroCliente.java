package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.ClienteService;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JDialog {

    private final ClienteService clienteService;

    public TelaCadastroCliente(JFrame parent, ClienteService clienteService) {
        super(parent, "Criar Conta de Cliente", true);
        this.clienteService = clienteService;

        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // --- Formulário ---
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNome = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JTextField txtCpf = new JTextField();
        JTextField txtNumero = new JTextField();
        JTextField txtEmail = new JTextField();

        painelFormulario.add(new JLabel("Nome de Usuário:"));
        painelFormulario.add(txtNome);
        painelFormulario.add(new JLabel("Senha:"));
        painelFormulario.add(txtSenha);
        painelFormulario.add(new JLabel("CPF (Apenas números):"));
        painelFormulario.add(txtCpf);
        painelFormulario.add(new JLabel("Telefone:"));
        painelFormulario.add(txtNumero);
        painelFormulario.add(new JLabel("Email:"));
        painelFormulario.add(txtEmail);

        add(painelFormulario, BorderLayout.CENTER);

        // --- Botões ---
        JPanel painelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar Cadastro");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> dispose());

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String senha = new String(txtSenha.getPassword());
            String cpf = txtCpf.getText().trim();
            String numero = txtNumero.getText().trim();
            String email = txtEmail.getText().trim();

            if (nome.isEmpty() || senha.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha pelo menos Nome, Senha e CPF!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Validação simples de CPF;
            if(cpf.length()!=14) {
                JOptionPane.showMessageDialog(this, "CPF no formato inválido(Ex:XXX.XXX.XXX-XX)", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(cpf.charAt(3)!='.' ||cpf.charAt(7)!='.'|| cpf.charAt(11)!='-') {
                JOptionPane.showMessageDialog(this, "CPF no formato inválido(Ex:XXX.XXX.XXX-XX)", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }


            try {
                // Cria a instância passando 'null' pro ID (banco gera)
                Cliente novoCliente = new Cliente(null, nome, senha, cpf, numero, email);

                // O Service fará a validação se tem "admin" no nome
                clienteService.salvarCliente(novoCliente);

                JOptionPane.showMessageDialog(this, "Conta criada com sucesso! Faça seu login.");
                dispose();
            } catch (Exception ex) {
                // Exibe a mensagem da exceção do Service (Ex: Nome reservado)
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);
    }
}
