Projeto de Cadastro de Pessoas - Backend

Este é o backend de um sistema de cadastro de pessoas desenvolvido utilizando Java com Spring Boot. O projeto oferece uma API RESTful para gerenciar informações pessoais básicas, como nome, CPF, data de nascimento, altura, peso e sexo.
Tecnologias Utilizadas

    Java 11
    Spring Boot 2.5.4
    Spring Data JPA
    Maven

Funcionalidades Principais

    Cadastro de Pessoas: Endpoint POST /people para salvar novas pessoas.
    Listagem de Pessoas: Endpoint GET /people para listar todas as pessoas cadastradas.
    Consulta por ID: Endpoint GET /people/{id} para buscar uma pessoa pelo seu ID.
    Atualização de Pessoas: Endpoint PUT /people/{id} para atualizar informações de uma pessoa existente.
    Exclusão de Pessoas: Endpoint DELETE /people/{id} para remover uma pessoa do sistema.
    Cálculo de Peso Ideal: Endpoint GET /people/{id}/ideal-weight para calcular o peso ideal baseado na altura e sexo da pessoa.

Pré-requisitos

    Java JDK 11 ou superior
    Maven 3.x

A aplicação estará acessível em http://localhost:8080.

Configurações Adicionais

Para configurar o banco de dados ou outras propriedades do Spring, consulte o arquivo src/main/resources/application.properties.
Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request com melhorias.
