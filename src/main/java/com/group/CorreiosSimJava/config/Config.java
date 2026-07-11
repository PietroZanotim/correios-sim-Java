package com.group.CorreiosSimJava.config;

import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

public class Config implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int escolha=-1;

        while(flag) {
            System.out.println("====== Correios Sim System ======");
            System.out.println("1. Entrar com meu usuário.");
            System.out.println("2. Criar usuário.");
            System.out.println("3. Encerrar programa.");
            System.out.println();
            System.out.println("Digite: ");

        }
    }
}
