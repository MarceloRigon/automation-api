# Projeto de Testes de API com RestAssured

Este projeto tem como objetivo automatizar os testes de uma API utilizando o framework **RestAssured** para testes de integração e **JUnit** para estrutura de testes. O projeto é organizado de forma a testar funcionalidades específicas de uma API (como autenticação, criação de produtos, consulta de status e outras operações CRUD).

## Estrutura do Projeto

O projeto é dividido naseguinte maneira:

- **src/test/java/api**: Contém os testes automatizados com o **JUnit**.
    - Nesta pasta constem os arquivos: 
        - **ApiTestException**: Dados de configuração de exceção.
        - **AuthTest**: Script de teste de "criarTokenAutenticacao", "buscarUsuarioParaAutenticacao", "buscarStatusDaAplicacao".
        - **BaseTest**: Dados de configuração de URL Base da API.
        - **ProductTest**: Script de teste de "buscarProdutosComAutenticacao", "criarProduto", "buscarTodosOsProdutos", "buscarProdutoPorId".
- **target**: Pasta gerada pelo Maven após a execução dos testes, contendo artefatos de compilação e relatórios de execução.
- **pom.xml**: Arquivo gerado automaticamente, onde configuramos as dependências, plugins, configurações de build e outros parâmetros necessários para construir e gerenciar o projeto
## Pré-Requisitos

Para rodar este projeto, você precisará de:

- **Java 8 ou superior**: Certifique-se de ter o **JDK 8** ou superior instalado.
- **Maven 3.6 ou superior**: Para gerenciar dependências e rodar os testes.
- **IDE de sua preferência**: Recomendado usar o IntelliJ IDEA ou Eclipse para facilitar a navegação no código e execução dos testes.

## Configuração do Projeto

1. **Clone o repositório**:

```bash
git clone https://github.com/seu-usuario/testes-api-restassured.git
cd testes-api-restassured
```
2. **Instale as dependências**:

Use o Maven para instalar todas as dependências do projeto:
```bash
mvn install
```

**Caso tenha algum problema com as dependências, basta entrar no menu do Maven e efetuar um Reload dos arquivos.**
## Melhoria Futura no projeto

Por conta do tempo de criação do projeto, foi feito em uma estrutura mais simples para rodar os testes solicitados, porém podem ser feitas algumas melhorias, criando uma estrutura mais eficaz, como por exemplo: 
```bash
 - 📁 src
    - 📁 main
        - 📁 java
            - 📁 api
                - 📁 core (Configuração base)
                - BaseTest.java 
                - 📁 services (Lógica de chamadas à API)
                - ProductService.java
                - AuthService.java
                - 📁 Exceptions (Tratamento de exceção)
                - ApiException 
    - 📁 test
        - 📁 java
            - 📁 api
            - ProductTest.java (Scripts de teste de Produto)
            - AuthTest.java (Script de teste de Autenticação)
```
Com essa estrutura acima é possível criar testes mais escaláveis e de fácil manutenção.
## Executando os Testes
Você pode rodar os testes utilizando o Maven ou diretamente pela IDE:

Usando o Maven:
Para rodar todos os testes de uma vez, execute:
```bash
mvn test
```
Isso executará os testes configurados no projeto e gerará um relatório de execução dentro da pasta target.

## Executando um Teste Específico:
Para rodar um teste específico, você pode usar o seguinte comando:

```bash
mvn -Dtest=NomeDaClasseDeTeste test
```
Exemplo para rodar o teste AuthTest:

```bash
mvn -Dtest=AuthTest test
```
## Bug Encontrado

Durante o teste de **"criarTokenAutenticacao"**, ao executar ele retorna o seguinte: 

`api.ApiTestException: Erro ao criar token de autenticação: 1 expectation failed.
Expected status code <200> but was <400>.`

Mesmo se mudarmos o **.statusCode()** para algum que o sistema espera, ele retorna falha no teste. 

Ao configurar o ambiente no **Postman** e efetuar o teste, utilizando o Bearer Token e as credenciais de login informadas na documentação, a mesma falha ocorre, retornando a seguinte mensagem:

Dados utilizados para o teste: 
```bash
{
    "username": "emilys",
    "password": "emilyspass"
}

Bearer Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MjEwNTM2OTAsImV4cCI6MTcyMTA1NzI5MH0.-Sx6wqGBBm0vCNTYXYR30XLijgeKufAZC14BRzMr388
```
`
401 Unauthorized: {
    "message": "Token Expired!"
}
`

Efetudado o teste porém enviando o token via **Basic Auth** no body da requisição via **Postman**, ocorre a mesma falha, ou seja, é uma falha na própria aplicação.

## Logs
Implementado ao final de todos os scripts de test o metódo **log().all()**, para mostrar os dados de resultado do teste executado.

## Relatório
Foi implementado o plugin **Maven Surefire Report Plugin** para visualização dos resultados dos testes. Ao executar os testes o resultado fica armazenado na pasta:

```bash
target/surefire-reports
```
Terão os arquivos em XML dos testes que foram executados.

## Pipeline

Criado arquivo Jenkinsfile para executar os testes em ume esteira.
