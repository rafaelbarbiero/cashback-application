[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=albums-sales-cashback&metric=alert_status)](https://sonarcloud.io/dashboard?id=albums-sales-cashback) 
[![Build Status](https://travis-ci.com/rafaelbarbiero/cashback-application.svg?branch=master)](https://travis-ci.com/rafaelbarbiero/cashback-application)
[![codecov](https://codecov.io/gh/rafaelbarbiero/albums-sales-cashback/branch/master/graph/badge.svg)](https://codecov.io/gh/rafaelbarbiero/albums-sales-cashback)

# Albums Sales Cashback

### Tecnologias
* Java 8
* Maven
* Spring Boot
* Spring Data MongoDB
* Spring Cloud OpenFeign
* Spring Redis
* JUnit
* Swagger
* JaCoCo
* Mockito
* SonarLint
### Execução local
* **Requisitos**
    1. Java 8
    2. Maven 3.5.3

* **Instruções**
    1. Navegar até o diretório raiz do projeto
    2. Executar o comando Maven para a construção da aplicação **mvn clean install**
    3. Navegar até o diretório **{PROJECT_DIR}/api/target/**
    3. Executar o comando de inicialização **java -jar cashback-api-0.0.1-SNAPSHOT.jar**
    5. É possível alterar os dados de conexão da base com os argumentos de programa **--mongo_ip=IP** e **--mongo_port=PORT**
    6. É possível alterar os dados de conexão da base de cache com o argumentos **--redis_host=IP**, **--redis_port=PORT**
    7. Por padrão, a aplicação iniciará conectando em uma máquina online na **Amazon AWS**

### Documentação da API
_Para visualizar a documentação completa da API, inicie a aplicação e navegue até a URL:_ **http://IP_ADDRESS:PORT/swagger-ui.html**
