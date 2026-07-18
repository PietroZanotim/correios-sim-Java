package com.group.CorreiosSimJava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class CorreiosSimJavaAppApplication implements CommandLineRunner {

    // Em vez de injetar os services aqui, nós injetamos a própria tela inicial!
    // A tela, por sua vez, vai pedir os services que ela precisa no próprio construtor dela.
    private final TelaLogin telaLogin; // Ou TelaPrincipal, dependendo de qual você quer abrir primeiro

    // Injeção de dependência via construtor
    public CorreiosSimJavaAppApplication(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }

    public static void main(String[] args) {
        // Desativa o modo headless para permitir a interface gráfica do Swing
        new SpringApplicationBuilder(CorreiosSimJavaAppApplication.class)
                .headless(false)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Spring Boot carregado! Iniciando interface gráfica...");

        // Inicia a janela na thread correta do Swing
        SwingUtilities.invokeLater(() -> {
            telaLogin.setVisible(true); // Agora a variável existe e foi injetada pelo Spring!
        });
    }
}
