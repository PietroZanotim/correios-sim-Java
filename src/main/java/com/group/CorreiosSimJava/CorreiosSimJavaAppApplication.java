package com.group.CorreiosSimJava;

import com.group.CorreiosSimJava.service.AdminService;
import com.group.CorreiosSimJava.service.ClienteService;
import com.group.CorreiosSimJava.service.FreteService;
import com.group.CorreiosSimJava.gui.TelaLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.SwingUtilities;

@SpringBootApplication
public class CorreiosSimJavaAppApplication implements CommandLineRunner {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FreteService freteService;

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(CorreiosSimJavaAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin(freteService, adminService);
            telaLogin.setVisible(true);
        });
    }
}