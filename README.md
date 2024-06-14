# API Tivia REST

Esta aplicação é uma API REST desenvolvida em Java com Spring Boot para gerenciar o cadastro de beneficiários e seus respectivos documentos. Os dados são armazenados em um banco de dados SQL Server.

## Funcionalidades

A API fornece os seguintes endpoints:

#Beneficiarios
- **POST /beneficiarios**: Cadastrar um novo beneficiário.
- **GET /beneficiarios**: Listar todos os beneficiários cadastrados.
- **GET /beneficiarios**: Listar beneficiários especifico cadastrados.
- **PUT /beneficiarios/{id}**: Atualizar os dados cadastrais de um beneficiário.
- **DELETE /beneficiarios/{id}**: Remover um beneficiário.

#Documentos
- **POST /documentos**: Cadastrar um novo documento.
- **GET /documentos**: Listar todos os documentos cadastrados.
- **GET /documentos/{id}**: Listar documento a partir de seu id.
- **PUT /documentos/{id}**: Atualizar os dados cadastrais de um documento.
- **DELETE /documentos/{id}**: Remover um documento.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 2.7.0
- SQL Server
- JPA/Hibernate
- Swagger para documentação da API

## Requisitos

- Java 17 ou superior
- Maven
- SQL Server
