# Correios Sim System (Logística)

Sistema de gerenciamento logístico desenvolvido em Java, utilizando Spring Boot para persistência de dados e Swing para interface gráfica. O projeto implementa um fluxo completo de rastreamento de encomendas, gestão de usuários (Admin/Cliente) e cálculos de frete baseados em cubagem e distância.

## 🚀 Funcionalidades

- **Autenticação:** Login diferenciado para administradores e clientes.
- **Gestão de Clientes:** Cadastro de usuários com validação de regras de negócio.
- **Logística:** Criação de fretes com cálculo automático de valor baseado no "Peso Cubado" vs "Peso Real" e faixas de distância entre estados.
- **Painel Administrativo:** Visualização e alteração de status de fretes em tempo real.
- **Rastreamento:** Detalhamento completo de pacotes vinculados a cada frete.
- **Segurança:** Bloqueio de rotas administrativas para usuários comuns.

## 🛠 Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot (Spring Data JPA)
- **Interface:** Swing (Arquitetura baseada em Painéis/CardLayout)
- **Banco de Dados:** MySQL
- **ORM:** Hibernate (Estratégia de Herança JOINED)
- **Ferramentas:** Maven, Lombok (opcional), DBeaver (SQL)
