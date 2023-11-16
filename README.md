# aluguel-veiculos-api
API para gerenciamento de uma loja de aluguel de veículos.

## Dependências
- Spring Boot Starter Data JPA;
- Spring Boot Starter JDBC;
- Spring Boot Starter Web;
- PostgreSQL Driver.
- Project Lombok;
- Spring Boot Starter Test;
- Gson.

## Banco de dados
A aplicação utiliza o PostgreSQL como banco de dados.
Exemplo de Configuração no arquivo application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/aluguel-veiculos
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto = update
```

## Veículos

### Consultar Veículos

Endpoint: `GET /veiculos`

#### Parâmetros de Consulta

- **reservado** (Opcional): Filtra os veículos por reserva.
  - Tipo: Boolean
  - Exemplo: `GET /veiculos?reservado=true`

#### Resposta de Sucesso

- Status: 200 OK
- Corpo:
  ```json
  [
    {
    "id": 1,
    "modelo": "Ford Fiesta",
    "marca": "Ford",
    "cor": "Azul",
    "anoDeFabricacao": 2020,
    "placa": "ABC1234",
    "reservado": false
  },
  {
    "id": 2,
    "modelo": "Chevrolet Onix",
    "marca": "Chevrolet",
    "cor": "Preto",
    "anoDeFabricacao": 2021,
    "placa": "DEF5678",
    "reservado": true
  }
    // ... outros veículos
  ]

### Criar Veículo

Endpoint: `POST /veiculos`

#### Corpo da requisição

- **Modelo**: Modelo do veículo.
- **Marca**: Marca do veículo.
- **Cor**: Cor do veículo.
- **Ano de Fabricação**: Ano de fabricação do veículo.
- **Placa**: Placa do veículo.
- **Reservado**: Indica se o veículo está reservado.

  Exemplo:
  ```json
  {
    "modelo": "Volkswagen Gol",
    "marca": "Volkswagen",
    "cor": "Branco",
    "anoDeFabricacao": 2020,
    "placa": "GHI9012",
    "reservado": false
  }

#### Resposta de Sucesso
- Status: 201 CREATED
- Corpo:
  ```json
  {
    "status": "success",
    "message": "Veículo inserido com sucesso!"
  }

#### Resposta de Erro
- Status: 400 BAD REQUEST
- Corpo:
  ```json
  {
    "status": "error",
    "message": "Não foi possível inserir veículo."
  }

### Atualizar Veículo

Endpoint: `PUT /veiculos`

#### Corpo da requisição
- **ID** (obrigatório): Identificador único do veículo;
- **Modelo**: Modelo do veículo;
- **Marca**: Marca do veículo;
- **Cor**: Cor do veículo;
- **Ano de Fabricação**: Ano de fabricação do veículo;
- **Placa**: Placa do veículo;
- **Reservado**: Indica se o veículo está reservado.

  Exemplo:
  ```json
  {
    "id": 1,
    "modelo": "Volkswagen Gol",
    "marca": "Volkswagen",
    "cor": "Prata",
    "anoDeFabricacao": 2020,
    "placa": "GHI9012",
    "reservado": true
  }

#### Resposta de Sucesso
- Status: 200 OK
- Corpo:
  ```json
  {
    "status": "success",
    "message": "Veículo alterado com sucesso!"
  }

#### Resposta de Erro
- Status: 400 BAD REQUEST
- Corpo:
  ```json
  {
    "status": "error",
    "message": "Não foi possível alterar veículo."
  }

### Apagar Veículo

Endpoint: `DELETE /veiculos/{id}`

#### Parâmetros

- **ID** (Obrigatório): Identificador único do veículo.
  - Tipo: Inteiro
  - Exemplo: `DELETE /veiculos/1`

#### Resposta de Sucesso
- Status: 204 NO CONTENT
