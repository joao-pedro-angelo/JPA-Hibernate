# JPA x JDBC

> CRUD que utiliza  a API JDBC para persistir os dados:
> > https://github.com/joao-pedro-angelo/ClassicBank


---
# JDBC

> Especificação para acesso a banco de dados relacionais em Java

A API JDBC é uma camada de abstração do protocolo de comunicação com o banco de dados.

Exemplo de código que utiliza a API JDBC:
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

Mesmo esta API sendo uma abstração que visa causar impacto mínimo ao trocar de banco de dados,
a JDBC ainda resulta em um alto acoplamento entre a aplicação e o banco de dados. Mesmo que algum design patterns
seja utilizado, como o DAO Pattern, ainda há o acoplamento elevado entre a aplicação e o banco de dados.

Logo, qualquer mudança, mesmo que uma simples troca no nome de determinada coluna, gera um alto impacto na aplicação,
resultado em mudanças em diversas partes do código.

Veja no código acima, que é necessário conhecer a linguagem de busca estruturada, SQL,
e além disso, o acesso ao banco de dados é feito de forma direta, tendo que utilizar nome da tabela,
às vezes nome de alguma coluna, o que resulta em alto acoplamento.


---
# JPA

> Especificação mais moderna para acesso a banco de dados relacionais em Java

A JPA surge como uma alternativa à API JDBC, de modo que resulte em um acoplamento menor
entre a aplicação e o banco de dados. Além disso, com a JPA, o código fica muito menos verboso, sendo mais
prático de ser utilizado.


## ORM

A JPA utiliza a técnica Object-Relation-Mapping (ORM) para realizar a persistência de dados.
Esta técnica aproxima o paradigma orientado a objetos dos bancos de dados relacionais.

Com o mapeamento objeto relacional, a persistência de dados fica muito mais simples, de modo que as consultas SQL nem
sempre são necessárias e o acoplamento com o banco de dados é bem menor que com JDBC puro.

Vale lembrar, que a JPA utiliza o JDBC por baixo dos panos. Mas a camada de abstração é maior.


## JPA Hibernate

A JPA é uma especifação. O Hibernate é uma das implementações da JPA


---
# JPA em Prática

[Configuração JPA](/teoria/ConfigurandoJPA.md)<br><br>
