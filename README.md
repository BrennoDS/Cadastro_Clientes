
# Cadastro Clientes

API REST para cadastro de clientes, desenvolvido com Java e SpringBoot(Sem validação de segurança até o momento)

Projeto feito pra estudar Flyway migrations e PostgreSQL no Docker


## Funcionalidades

- Criar clientes
- Editar clientes
- Excluir clientes
- Listar clientes
- Migração de banco com Flyway

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker

## Arquitetura
Arquitetura em camadas:

- **Controller** — recebe as requisições HTTP
- **Service** — contém as regras de negócio
- **Repository** — comunicação com o banco de dados
- **DTOs** — transferência de dados entre camadas

## Como rodar o projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/BrennoDS/Cadastro_Clientes
```

### 2. Subir o banco com Docker
```bash
docker compose up -d
```

### 3. Configurar o application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cadastro_clientes
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 4. Rodar a aplicação
```bash
./mvnw spring-boot:run
```

## Banco de dados
O banco de dados é gerenciado pelo Flyway que executa as migrations ao executar a aplicação
Local: Local das migrations:
src/main/resources/db/migration

## Endpoints

| Método | Endpoint         | Descrição             | Status |
| ------ | ---------------- | --------------------- | ------ |
| GET    | /clientes        | Lista todos clientes  | 200    |
| POST   | /clientes        | Cria cliente          | 201    |
| PUT    | /clientes/{id}   | Atualiza cliente      | 200    |
| DELETE | /clientes/{id}   | Remove cliente        | 204    |

## Exemplo de requisiçoes

### POST /clientes
**Request:**
```json
{
  "nome": "João",
  "email": "joao@email.com"
}
```
**Response** `201 Created`:
```json
{
  "id": 1,
  "nome": "João",
  "email": "joao@email.com"
}
```


## Tratamento de erros
A API tem tratamento de erros com o @RestControllerAdvice
**Exemplo de resposta de erro:**
```json
{
  "error": "Cliente não encontrado com id: 99"
}
```

---

> Projeto desenvolvido para fins de estudo.
