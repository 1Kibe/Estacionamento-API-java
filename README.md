# 🚗 Estacionamento_0

Sistema completo para gestão de estacionamento, feito com **Spring Boot**, **JPA** e **MySQL**.

---

## 📦 Funcionalidades

- Cadastro de clientes, veículos, vagas, usuários e pagamentos
- Controle de entrada e saída de veículos
- API REST para todas as entidades
- Migração automática do banco com Flyway

---

## 🛠️ Tecnologias

- Spring Boot
- Spring Data JPA
- MySQL
- Flyway
- Lombok

---

## ▶️ Como rodar

1. Configure o banco no arquivo `src/main/resources/application.properties`
2. Execute:
   ```
   ./mvnw spring-boot:run
   ```
3. Acesse: [http://localhost:8080](http://localhost:8080)

---

## 📁 Estrutura

```
src/
 ├── main/java/com/ryan/estacionamento_0/
 │    ├── domain/      # Entidades
 │    ├── repository/  # Repositórios
 │    ├── service/     # Serviços
 │    ├── resource/    # Controllers REST
 └── resources/
      └── db/migration/ # Scripts Flyway
```

---

> Projeto para estudos e evolução em backend Java 🚀
