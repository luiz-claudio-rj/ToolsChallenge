# Projeto de Transações Financeiras

Este projeto é uma aplicação Spring Boot que gerencia transações financeiras, incluindo a criação, estorno e validação
de transações. Utiliza o Maven como ferramenta de build e gerenciamento de dependências.

## Funcionalidades

- **Criação de Transações**: Permite a criação de transações financeiras com detalhes como valor, data, hora,
  estabelecimento, e forma de pagamento.
- **Estorno de Transações**: Suporta o estorno de transações existentes, alterando o status da transação para refletir o
  estorno.
- **Validação de Transações**: Realiza a validação de dados da transação antes de sua criação, garantindo a integridade
  e a validade dos dados inseridos.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação.
- **Spring Boot**: Framework para facilitar a configuração e o desenvolvimento de aplicações baseadas em Spring.
- **Maven**: Ferramenta de automação de compilação utilizada para gerenciar as dependências do projeto.

## Estrutura do Projeto

- `src/main/java/com/example/desafio/`: Diretório principal do código fonte.
    - `controllers/`: Controladores que expõem endpoints da API.
    - `dto/`: Data Transfer Objects usados para transferir dados entre subcamadas.
    - `model/`: Entidades do domínio.
    - `repository/`: Interfaces do JPA para interação com o banco de dados.
    - `service/`: Serviços que contêm a lógica de negócios.
    - `validators/`: Validadores para verificar a integridade dos dados das transações.
- `src/test/java/com/example/desafio/`: Diretório de testes.
    - `validators/`: Testes para os validadores de requisição de transação.

## Como Executar

1. Clone o repositório para sua máquina local.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.
4. A aplicação estará disponível em `http://localhost:8080`.

## Testes

Para executar os testes, utilize o comando `mvn test`. Isso irá executar todos os testes unitários e de integração
configurados no projeto.
