package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.service.ClienteService;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JDialog {

    public TelaCadastroCliente(JFrame parent, ClienteService clienteService) {
        super(parent, "Novo Cadastro", true);
        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos de texto
        JTextField txtNome = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JTextField txtCpf = new JTextField();
        JTextField txtNumero = new JTextField();
        JTextField txtEmail = new JTextField();

        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(txtNome);
        painelFormulario.add(new JLabel("Senha:"));
        painelFormulario.add(txtSenha);
        painelFormulario.add(new JLabel("CPF:"));
        painelFormulario.add(txtCpf);
        painelFormulario.add(new JLabel("Número:"));
        painelFormulario.add(txtNumero);
        painelFormulario.add(new JLabel("Email:"));
        painelFormulario.add(txtEmail);

        add(painelFormulario, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> dispose());

        btnSalvar.addActionListener(e -> {
            // TODO: pegar os `.getText()` dos campos acima, usar o try-catch para as
            // exceções customizadas (InvalidCpfException, etc) e salvar no banco.
            JOptionPane.showMessageDialog(this, "Lógica de salvamento será implementada pelo Back-end!");
            dispose();
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);
    }
}