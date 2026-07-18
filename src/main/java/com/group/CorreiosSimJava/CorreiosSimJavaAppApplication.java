package com.group.CorreiosSimJava;

import com.group.CorreiosSimJava.gui.JanelaPrincipal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class CorreiosSimJavaAppApplication implements CommandLineRunner {

    private final JanelaPrincipal janelaPrincipal;

    // Injetamos agora a JanelaPrincipal
    public CorreiosSimJavaAppApplication(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
    }

    public static void main(String[] args) {
        // Desativa o modo headless para abrir interface
        new SpringApplicationBuilder(CorreiosSimJavaAppApplication.class)
                .headless(false)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Inicia a interface
        SwingUtilities.invokeLater(() -> {
            janelaPrincipal.setVisible(true);
        });
    }
}
