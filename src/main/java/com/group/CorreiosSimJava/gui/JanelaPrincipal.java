package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Usuario;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.AdminService;
import com.group.CorreiosSimJava.service.ClienteService;
import com.group.CorreiosSimJava.service.FreteService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class JanelaPrincipal extends JFrame {

    private final FreteService freteService;
    private final AdminService adminService;
    private final ClienteService clienteService;

    // O CardLayout é o que permite trocar entre PainelLogin, PainelAdmin, etc.
    private final CardLayout cardLayout;
    private final JPanel painelCentral;

    public JanelaPrincipal(FreteService freteService, AdminService adminService, ClienteService clienteService) {
        this.freteService = freteService;
        this.adminService = adminService;
        this.clienteService = clienteService;

        setTitle("Correios Sim System - Sistema de Logística");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);
        add(painelCentral, BorderLayout.CENTER);

        // Adiciona a tela de login inicialmente
        PainelLogin painelLogin = new PainelLogin(this, adminService, clienteService);
        painelCentral.add(painelLogin, "LOGIN");
    }

    // Chamado pelo PainelLogin após autenticação
    public void fazerLogin(Usuario usuario) {
        if (usuario instanceof Admin) {
            PainelAdmin painelAdmin = new PainelAdmin(this, freteService, (Admin) usuario);
            painelCentral.add(painelAdmin, "ADMIN");
            cardLayout.show(painelCentral, "ADMIN");
        } else if (usuario instanceof Cliente) {
            PainelCliente painelCliente = new PainelCliente(this, freteService, (Cliente) usuario);
            painelCentral.add(painelCliente, "CLIENTE");
            cardLayout.show(painelCentral, "CLIENTE");
        }
    }

    public void fazerLogout() {
        cardLayout.show(painelCentral, "LOGIN");
    }
}
