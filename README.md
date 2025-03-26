# 📦 API XML - Geração de Clientes em XML

Esta é uma API REST desenvolvida com **Spring Boot 3.4.3** e **JDK 17** que gera e retorna dados no formato **XML**. Ela simula uma lista de clientes e permite o consumo via endpoints RESTful com suporte à serialização automática em XML.

---

## 🚀 Tecnologias Utilizadas

- ✅ Java 17
- ✅ Spring Boot 3.4.3
- ✅ Spring Web
- ✅ Jackson Dataformat XML
- ✅ Lombok

---

## 📁 Estrutura do Projeto

```bash
src
├── controller        # Camada REST (ex: ClienteController)
├── dto               # Objetos de transferência (ClienteDto, ClientesDto)
├── service           # Camada de regra de negócio (ClienteService)
└── util              # Classe utilitária de dados simulados (ClienteDataFactory)
```

---

## 📡 Endpoints da API

### 🔹 `GET /api/v1/xml/clientes`

Retorna uma **lista de clientes** no formato XML.

📦 **Exemplo de resposta**:
```xml
<clientes>
  <cliente>
    <nome>Joao Pereira</nome>
    <cpf>123.456.789-00</cpf>
    <renda>7500.0</renda>
  </cliente>
  <cliente>
    <nome>Ana Souza</nome>
    <cpf>987.654.321-00</cpf>
    <renda>6500.0</renda>
  </cliente>
</clientes>
```

---

### 🔹 `GET /api/v1/xml/cliente`

Retorna um **cliente único** no formato XML.

📦 **Exemplo de resposta**:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<clientes>
  <cliente>
    <nome>Joao Pereira</nome>
    <cpf>123.456.789-00</cpf>
    <renda>7500.0</renda>
  </cliente>
  <cliente>
    <nome>Ana Souza</nome>
    <cpf>987.654.321-00</cpf>
    <renda>6500.0</renda>
  </cliente>
</clientes>
```

---



## 📚 Documentação da API (Swagger)

A documentação interativa da API está disponível via **Swagger UI**, gerada automaticamente com **SpringDoc OpenAPI 3**.

🔗 Acesse pelo navegador: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## ✅ Como executar o projeto localmente

### 🔧 Pré-requisitos

- Java 17+
- Maven 3.8+
- Git

### ▶️ Executando

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

Acesse a API em: [http://localhost:8080/api/v1/xml](http://localhost:8080/api/v1/xml)

---

## 🔐 Content-Type

Lembre-se de enviar nas requisições o cabeçalho correto:

```http
Accept: application/xml
```

---

## ✍️ Autor

**Marcos Vieira Matias**  
[LinkedIn](https://www.linkedin.com/in/seu-perfil)  
📧 marcos_klony@hotmail.com

---

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

Gerado em 17/03/2025.
