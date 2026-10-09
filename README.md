# ☕ CRUD JDBC — Java + MySQL

Projeto desenvolvido com o objetivo de estudar e praticar a integração entre **Java e banco de dados MySQL utilizando JDBC (Java Database Connectivity)**.

Durante o desenvolvimento, foram aplicados conceitos fundamentais de acesso a dados em Java, desde a criação da conexão com o banco até a implementação das operações de um CRUD.

## 📌 Sobre o projeto

A aplicação consiste em um CRUD de pessoas, permitindo realizar operações de:

- ➕ **Create** — cadastrar pessoas
- 🔎 **Read** — consultar e listar pessoas
- ✏️ **Update** — atualizar dados
- 🗑️ **Delete** — remover registros

O projeto utiliza o padrão **DAO (Data Access Object)** para separar a lógica de acesso ao banco de dados das demais partes da aplicação.

## 🛠️ Tecnologias utilizadas

- ☕ Java
- 🗄️ MySQL
- 🔌 JDBC
- 📦 Maven
- 🧩 DAO Pattern
- 🏭 Factory Pattern
- 💻 IntelliJ IDEA

## 🔌 Conexão com o banco

A aplicação utiliza o **JDBC** para estabelecer a comunicação entre o Java e o MySQL.

O JDBC fornece a API necessária para que aplicações Java possam se conectar ao banco, executar comandos SQL e processar os resultados retornados, usando o Driver correto para acesso ao banco de dados.

A conexão é centralizada na classe `DB`, facilitando o gerenciamento das conexões utilizadas pela aplicação.

## 📋 Operações CRUD

### Create

Cadastro de uma novo produto no banco:

```sql
INSERT INTO products (name, description, price, quantity, category)
VALUES (?, ?, ?, ?, ?);
```

### Read

Consulta dos registros armazenados no banco de dados:
```sql
SELECT * FROM products;
```

```sql
SELECT * FROM products WHERE id = ?;
```

### Update

Atualização dos dados de um produto: 
```sql
UPDATE products SET price = ?, quantity = ? WHERE id = ?;
```


### Delete

Remoção de registros:
```sql
DELETE FROM products WHERE id = ?;
```

## 🧩 Padrão DAO

O projeto utiliza o padrão **DAO (Data Access Object)** para organizar as operações relacionadas ao banco de dados.

Com essa abordagem, as responsabilidades ficam separadas:

```text
Entity
   ↓
DAO
   ↓
Database
```

A entidade representa os dados, enquanto o DAO concentra as operações de persistência.

## 🏭 DaoFactory

Também foi utilizado o padrão **Factory** para centralizar a criação das implementações dos DAOs.

Exemplo:

```java
DaoFactory.createProductDao();
```

Isso evita que a aplicação precise conhecer diretamente os detalhes de criação do objeto DAO.

## ⚙️ Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/Marcossousadev/cadastro-loja-jdbc.git
```

### 2. Configure o banco de dados

Crie um banco MySQL:

```sql
CREATE DATABASE loja;
```

Depois, crie a tabela utilizada pela aplicação.

```sql
CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
  description VARCHAR(255),
  price DECIMAL(10, 2) NOT NULL,
  quantity INT NOT NULL,
  category VARCHAR(50)
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. Configure as credenciais

Configure no projeto, no arquivo db.properties:

```text
user
password
dburl
mantenha useSSL=false
```

de acordo com as configurações do seu MySQL.

### 4. Execute o projeto

Abra o projeto no IntelliJ IDEA e execute as classes Mains que temos na raiz do projeto, de acordo com cada operação:

```text
MainDeleteById.java
MainFindAll
MainFindById
MainInsert
MainUpdateStockByIdProduct
```

## 🎯 Objetivos de aprendizado

Este projeto foi desenvolvido como parte dos meus estudos de **Java Back-end**, com foco em compreender os fundamentos antes de avançar para abstrações como JPA e Spring Data JPA, assim entendo a base.

Principais conceitos praticados:

- JDBC
- Conexão com banco de dados
- `Connection`
- `PreparedStatement`
- `ResultSet`
- SQL
- CRUD
- DAO
- Factory
- Tratamento de exceções
- Maven
- Integração Java + MySQL
