package com.group.CorreiosSimJava;

import com.group.CorreiosSimJava.entities.UsuarioImpl.Admin;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.service.AdminService;
import com.group.CorreiosSimJava.service.ClienteService;
import com.group.CorreiosSimJava.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CorreiosSimJavaAppApplication implements CommandLineRunner {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FreteService freteService;

    public static void main(String[] args) {
        SpringApplication.run(CorreiosSimJavaAppApplication.class, args);
    }

    @Override
    public void run(String[] args) {
        Iniciar();
    }

    public void Iniciar() {

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int escolha = -1;

        while (flag) {
            System.out.println("====== Correios Sim System ======");
            System.out.println("1. Entrar com meu usuário.");
            System.out.println("2. Criar usuário.");
            System.out.println("3. Encerrar programa.");
            System.out.println();
            System.out.println("Digite: ");

            try {
                escolha = sc.nextInt();
                if (escolha < 1 || escolha > 3) {
                    System.out.println("Valor inválido.");
                    System.out.println();
                }
            } catch (RuntimeException e) {
                System.out.println("Valor inválido.");
                System.out.println();
                continue;
            }

            if (escolha == 3) break; // Encerrar o programa;

            if (escolha == 1) { // Entrando com usuario;

            } else { // Criando o usuário;
                sc.nextLine();
                String nome, senha, cpf, numero, email;

                System.out.println();
                while (true) {
                    try {

                        System.out.println("Digite o nome: ");
                        nome = sc.nextLine();
                        System.out.println();
                        System.out.println("Digite a senha: ");
                        senha = sc.nextLine();
                        System.out.println();
                        System.out.println("Digite o CPF: ");
                        cpf = sc.nextLine();
                        System.out.println();
                        System.out.println("Digite o número: ");
                        numero = sc.nextLine();
                        System.out.println();
                        System.out.println("Digite o email: ");
                        email = sc.nextLine();
                        System.out.println();

                        Cliente novoUsuario = new Cliente(null, nome, senha, cpf, numero, email);
                        clienteService.salvarUsuario(novoUsuario);

                        System.out.println("| ========================== |");
                        System.out.println("| Usuário salvo com sucesso! |");
                        System.out.println("| ========================== |");
                        sc.nextLine();
                        break;

                    } catch (RuntimeException e) {
                        System.out.println("Valor inválido.");
                        System.out.println();
                        continue;
                    }

                }
            }
        }

        System.out.println("Encerrando sistema...");
    }
}
