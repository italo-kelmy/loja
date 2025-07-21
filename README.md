# 🛒 Loja Virtual - API REST com Spring Boot, JWT, HTTPS e MySQL

Este é um projeto de uma API REST de uma loja de eletrônicos, desenvolvida em **Java com Spring Boot**, utilizando **autenticação JWT**, **HTTPS forçado**, **banco de dados MySQL** e **testes unitários** com JUnit 5 e Mockito.

O sistema permite que usuários se cadastrem, façam login e interajam com produtos, carrinho e compras — tudo isso com controle de acesso seguro.

---

## ✅ Funcionalidades

- Cadastro e login de usuários (JWT gerado no login)  
- Adição e listagem de produtos eletrônicos  
- Adição de produtos ao carrinho  
- Finalização de compras  
- Apenas usuários autenticados podem comprar ou adicionar ao carrinho  
- HTTPS obrigatório (redirecionamento automático)  
- Integração com banco de dados **MySQL**  
- Testes unitários com JUnit e Mockito  

---

## 🔐 Segurança

- Toda a autenticação é feita via **JWT**  
- O token é necessário para acessar endpoints protegidos, enviado no cabeçalho HTTP `Authorization` assim:


- O projeto força o uso de **HTTPS** em todas as requisições  

---

## 🧪 Testes

Foram implementados testes unitários com:

- **JUnit 5**  
- **Mockito**  

Cobertura de testes para:  
- Usuário  
- Produto eletrônico  
- Carrinho  
- Compra  
