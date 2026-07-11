package com.group.CorreiosSimJava;

import com.group.CorreiosSimJava.entities.Usuario;
import com.group.CorreiosSimJava.entities.UsuarioImpl.Cliente;
import com.group.CorreiosSimJava.exceptions.*;
import com.group.CorreiosSimJava.service.AdminService;
import com.group.CorreiosSimJava.service.ClienteService;
import com.group.CorreiosSimJava.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;

import java.util.Scanner;

@SpringBootApplication
public class CorreiosSimJavaAppApplication implements CommandLineRunner {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FreteService freteService;

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(CorreiosSimJavaAppApplication.class, args);
    }

    @Override
    public void run(String[] args) {
        Iniciar();
    }

    public void Iniciar() {


        boolean flag = true;
        int escolha = -1;
        while (flag) {
            System.out.println();
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
            sc.nextLine();
            if (escolha == 3) break; // Encerrar o programa;

            if (escolha == 1) { // Entrando com usuario;
                String nome, senha;
                while(true) {
                    try {
                        System.out.println("Digite o nome do usuário: ");
                        nome = sc.nextLine();

                        if(!("admin".equals(nome))){ // Caso nao seja admin;
                            Cliente cliente = clienteService.findByName(nome);

                            System.out.println("Digite a senha: ");
                            senha = sc.nextLine();
                            System.out.println();
                            if(!senha.equals(cliente.getSenha())) throw new InvalidPasswordException("Senha inválida!");

                            TelaCliente(cliente); //Menu Cliente;
                        }
                        else { // Caso seja admin;
                            System.out.println("Digite a senha: ");
                            senha = sc.nextLine();

                            char a1= senha.charAt(5);
                            char a2= senha.charAt(6);
                            String concat = String.valueOf(a1);
                            concat = concat.concat(String.valueOf(a2));
                            int id = Integer.valueOf(concat);


                            System.out.println();
                        }
                    }
                    catch (InvalidCpfException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                    catch (DatabaseInvalidException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                    catch (InvalidPasswordException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                    catch (NullPointerException e) {
                        System.out.println("Formato digitado inválido!");
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                }

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
                        System.out.println("Digite o CPF (XXX.XXX.XXX-XX): ");
                        cpf = sc.nextLine();
                        validaCpf(cpf);
                        System.out.println();
                        System.out.println("Digite o número(XX XXXXX-XXXX): ");
                        numero = sc.nextLine();
                        validaNumero(numero);
                        System.out.println();
                        System.out.println("Digite o email: ");
                        email = sc.nextLine();
                        validaEmail(email);
                        System.out.println();

                        Cliente novoUsuario = new Cliente(null, nome, senha, cpf, numero, email);
                        clienteService.saveUser(novoUsuario);

                        System.out.println("| ========================== |");
                        System.out.println("| Usuário salvo com sucesso! |");
                        System.out.println("| ========================== |");
                        System.out.println("| Digite enter para continuar |");
                        sc.nextLine();
                        break;

                    }
                    catch (InvalidCpfException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                    catch (InvalidEmailException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                    catch (InvalidNumberException e){
                        System.out.println(e.getMessage());
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                    catch (RuntimeException e) {
                        System.out.println("Valor inválido.");
                        System.out.println("Tente novamente.");
                        System.out.println();
                        continue;
                    }
                }
            }
        }
        System.out.println("Encerrando sistema...");
    }

    public void TelaCliente(Cliente cliente){
        int escolha;
        boolean flag = true;
        while(flag) {
            System.out.println();
            System.out.printf("====== Olá! %s ======\n", cliente.getNome());
            System.out.println("1. Criar novo frete.");
            System.out.println("2. Verificar fretes existentes.");
            System.out.println("3. Verificar saldo.");
            System.out.println("4. Sair.");
            System.out.println();

            System.out.println("Digite: ");
            try {
                escolha = sc.nextInt();
                if (escolha < 1 || escolha > 3) {
                    System.out.println("Valor inválido.");
                    System.out.println();
                    sc.nextLine();
                    continue;
                }
            }
            catch (RuntimeException e) {
                System.out.println("Valor inválido.");
                System.out.println("Tente novamente.");
                System.out.println();
                continue;
            }

            switch(escolha) {
                case 1:
                break;

                case 2:
                break;

                case 3:
                    System.out.printf("Saldo de %s:\n", cliente.getNome());
                    System.out.printf("| ================= |");
                    System.out.printf("| R$ %.2f |", cliente.getSaldo());
                    System.out.printf("| ================= |");
                break;

                case 4:
                    flag = false;
                    System.out.println("Saindo do menu do cliente...");
                    System.out.println("| Digite enter para continuar |");
                    sc.nextLine();
                break;
            };
        }
    }

    public void TelaAdmin() {

    }

    public void validaCpf(String cpf) {
        if(cpf.charAt(3)!= '.' || cpf.charAt(7) != '.' || cpf.charAt(11) != '-' || cpf.length()!=14){
            throw new InvalidCpfException("CPF digitado no formato inválido!");
        }
    }
    public void validaEmail(String email){
        if(!(email.contains("@gmail.com") || email.contains("@email.com") || email.contains("@hotmail.com"))){
            throw new InvalidEmailException("Email digitado inválido!");
        }
    }
    public void validaNumero(String numero) {
        if(numero.charAt(2)!=' ' || numero.charAt(8)!='-'){
            throw new InvalidNumberException("Número digitado no formato incorreto!");
        }
    }
}
