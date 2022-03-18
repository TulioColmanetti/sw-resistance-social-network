# API REST: Star Wars Resistence Social Network

Projeto final do módulo 08 (Desenvolvimento Web) do curso [Santander Coders | Web Full-Stack](https://app.becas-santander.com/pt/program/bolsas-santander-tecnologia-santander-coders-web-full-stack-2021), em parceria com a [Let's Code](https://letscode.com.br/).

### Visão Geral

O objetivo deste projeto foi desenvolver uma API REST utilizando Spring Framework e metodologia TDD para compartilhar recursos e serviços entre os rebeldes do universo Star Wars, permitindo armazenar informação sobre os mesmos, bem como os recursos que eles possuem.

### Funcionalidades Implementadas

- [x] Adicionar rebeldes
- [x] Listar todos os rebeldes
- [x] Listar rebelde por ID
- [x] Atualizar a localização do rebelde
- [x] Reportar um rebelde como um traidor
- [x] Negociar itens entre rebeldes
- [x] Gerar relatórios dos rebeldes
- [x] Persistência em banco de dados H2 (memória/arquivo)
- [x] Criação de _collection_ do Postman para testes da API
- [x] Testes unitários de _controllers_ e _services_

### Tecnologias Utilizadas

- Java 11 (linguagem de desenvolvimento);
- Maven (gerenciamento de dependências);
- Intellj IDEA Enterprise Edition (IDE);
- Git e GitHub (controle e versionamento de código fonte);
- Banco de dados H2 (persistência em memória em ambiente DEV);
- JUnit 5 e Mockito (testes unitários e de integração);

### Dependências

Dependências do Spring Boot versão **2.6.4**:
- Spring Web
- Spring Data JPA
- Lombok
- Spring Boot DevTools
- H2 Database
- Validation

Dependências externas:
- [Mapstruct](https://mapstruct.org/)

### Execução

Para executar o projeto localmente, basta digitar o seguinte comando em um terminal:

- Perfil de **desenvolvimento**:
```shell script
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Depois que o aplicativo estiver em execução, abra o navegador da Web de sua preferência e visite o seguinte endereço para acessar a API:

- API endpoint para CRUD de rebeldes: [http://localhost:8080/api/v1/rebels](http://localhost:8080/api/v1/rebels)
- API endpoint para serviços comuns da Resistência Rebelde: [http://localhost:8080/resistance-network](http://localhost:8080/resistance-network)

### Testes

Para executar o conjunto de testes completo, basta executar o seguinte comando:

```shell script
mvn clean test
```

### Links Úteis

- [Intellij Shortcuts Pallete](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
- [Spring Official Site](https://spring.io/)
- [JUnit 5 Official Site](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Official Site](https://site.mockito.org/)
- [Tests with Spring Boot](https://www.baeldung.com/spring-boot-testing)
- [REST Architectural Standard Reference](https://restfulapi.net/)
- [Richardson Maturity Model](https://restfulapi.net/richardson-maturity-model/)
- [Martin Fowler's Test Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html#TheImportanceOftestAutomation)
- [Spring Boot App Deploy on Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)
- [TDD in 5 Steps](https://www.xenonstack.com/blog/test-driven-development-python)
- [BDD Given-When-Then Style](https://martinfowler.com/bliki/GivenWhenThen.html)

### Agradecimentos

Agradeço à [Let's Code](https://www.linkedin.com/school/letscodebr/) e ao [Santander](https://www.linkedin.com/company/grupo-santander-brasil/) por promoverem esse curso.