# CRUD com JPA Hibernate
Este material aborda o uso da JPA para realizar operações básicas de criação, leitura, atualização e exclusão de dados em um banco de dados. Siga os passos detalhados abaixo para obter o resultado esperado.

Para entender as diferenças entre JPA e JDBC, consulte o documento: [JPA x JDBC](/teoria/JPA_x_JDBC.md).

Veja o projeto implementado em: [ProjetoJPA](/src/main/java/org/studies).


---
## Configuração
Para configurar o ambiente, siga os passos descritos em detalhes em: [ConfigurandoJPA](/teoria/ConfigurandoJPA.md).

1. Crie um projeto Java utilizando o gerenciador de dependências Maven.
2. Configure o arquivo **pom.xml** adicionando as dependências necessárias.
3. No diretório **resources**, crie um subdiretório chamado **META-INF**.
4. Dentro do diretório criado, crie o arquivo **persistence.xml**.
5. Realize as configurações necessárias no arquivo **persistence.xml**.


---
## Entidades
Para mapear as entidades do sistema para tabelas no banco de dados, siga as instruções em: [Mapeando Entidades](/teoria/MapeandoEntidades.md).

1. Crie uma classe para cada uma das entidades do sistema.
2. Utilize a anotação **@Entity** para marcar cada entidade.
3. Defina o nome da tabela correspondente usando **@Table(name = "NomeDaTabela")**.
4. Os atributos das entidades representarão as colunas nas tabelas.
5. Consulte [Outros Relacionamentos](/teoria/OutrosRelacionamentos.md) em caso de dúvidas sobre as anotações a serem utilizadas.


---
## JPA Util
A classe JPA Util é responsável por criar e retornar a EntityManager. Consulte detalhes em: [JPA Util](/src/main/java/org/studies/JPAUtil/CreateEntityManager.java).

```java
public static EntityManager createEntityManager(){
    return FACTORY.createEntityManager();
}
```


---
## JPA Create
```java
public static void createRecord() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager entityManager = emf.createEntityManager();

    entityManager.getTransaction().begin();

    Player p = new Player();
    p.setPassword("my-password");
    entityManager.persist(p);

    entityManager.getTransaction().commit();
    entityManager.close();
}
```


---
## JPA Read
```java
public static void retrieveRecord() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager entityManager = emf.createEntityManager();

    entityManager.getTransaction().begin();
    Long key = Long.valueOf(1);
    Player p = entityManager.find(Player.class, key);
    System.out.println(p.getPassword());

    entityManager.getTransaction().commit();
    entityManager.close();
}
```


---
## JPA Delete
```java
public static void deleteRecord() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager entityManager = emf.createEntityManager();

    entityManager.getTransaction().begin();
    Long key = Long.valueOf(2);
    Player p = entityManager.find(Player.class, key);
    entityManager.remove(p);

    entityManager.getTransaction().commit();
    entityManager.close();
}
```


---
## JPA Update
```java
public static void updateRecord() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager entityManager = emf.createEntityManager();

    entityManager.getTransaction().begin();
    Long key = Long.valueOf(2);
    Player p = entityManager.find(Player.class, key);

    p.setPassword("123def");

    entityManager.getTransaction().commit();
    entityManager.close();
}
```


---
## Extra

Para entender melhor o ciclo de vida de uma entidade, consulte: [Ciclo de Vida](/teoria/CicloDeVida.md).
