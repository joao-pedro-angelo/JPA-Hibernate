# JPA x JDBC

> Este documento explora as diferenças entre as tecnologias JDBC e JPA para acesso a bancos de dados relacionais em Java.

---
## JDBC

### Visão Geral

A API JDBC é uma especificação para acesso a bancos de dados relacionais em Java. Ela fornece uma camada de abstração sobre o protocolo de comunicação com o banco de dados e é implementada por drivers específicos para cada banco de dados.

### Exemplo de Uso

Aqui está um exemplo de código que utiliza a API JDBC:

```java
public void abrirConta(Conta conta){
    String sqlQuery = "INSERT INTO conta (numeroConta, saldo, cpf)" +
            "VALUES (?, ?, ?)";

    Connection connection = this.conexaoDB.recuperaConexao();

    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setInt(1, conta.numeroConta());
        preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
        preparedStatement.setString(3, conta.cliente().cpf());
        preparedStatement.execute();

        this.encerraConexoes(connection, preparedStatement);
    } catch (SQLException e){
        throw new RuntimeException(e);
    }
}
```

---
### Desafios

Apesar de ser uma abstração que minimiza o impacto de mudanças de banco de dados, a JDBC resulta em um alto acoplamento entre a aplicação e o banco de dados. Qualquer alteração na estrutura do banco de dados requer mudanças significativas no código da aplicação. Além disso, o acesso direto ao banco de dados através de SQL aumenta a complexidade e o acoplamento.

---
## JPA

### Visão Geral

A JPA (Java Persistence API) é uma especificação mais moderna para acesso a bancos de dados relacionais em Java. Ela oferece uma alternativa à JDBC com o objetivo de reduzir o acoplamento entre a aplicação e o banco de dados.

### ORM (Object-Relational Mapping)

A JPA utiliza a técnica Object-Relational Mapping (ORM) para mapear objetos Java para tabelas no banco de dados. Isso permite uma abstração mais elevada em comparação com o uso direto da JDBC. Com o ORM, as consultas SQL são menos frequentemente necessárias e o acoplamento com o banco de dados é reduzido.

---
### JPA Hibernate

O Hibernate é uma das implementações da JPA. Ele fornece funcionalidades adicionais e aprimoramentos sobre a JPA padrão, simplificando ainda mais o desenvolvimento de aplicações Java que interagem com bancos de dados relacionais.

Em resumo, a JPA oferece uma alternativa mais eficiente e de maior nível de abstração em comparação com a JDBC, resultando em código mais limpo, menos verboso e com menor acoplamento ao banco de dados.
