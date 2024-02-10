# Crud com JPA Hibernate

> Neste material iremos usar a JPA para criar, atualizar, deletar e ler
> dados. Siga os passos que estão detalhados para obter o resultado esperado.

> Veja as diferenças entre JPA e JDBC: [JPA x JDBC](/teoria/JPA_x_JDBC.md)

> Veja o projeto que foi implementado: [ProjetoJPA](/src/main/java/org/studies)

---
## Configuração

> Para detalhes, leia: [ConfigurandoJPA](/teoria/ConfigurandoJPA.md)

* 1 - Crie um projeto Java com o gerenciador de dependências Maven
* 2 - No diretório **resources**, crie outro diretório, intitulado de **META-INF**
* 3 - Neste novo diretório, crie o arquivo **persistence.xml**
* 4 - Configure o arquivo **persistence.xml**
* 5 - Em seguida, adicione as dependências necessárias no **pom.xml**


---
## Entidades

> Para detalhes, leia: [Mapeando Entidades](/teoria/MapeandoEntidades.md)

* 1 - Crie uma classe para cada uma das entidades do seu sistema
* 2 - Para que cada uma dessas entidades seja mapeada para uma tabela no banco de dados, marque-as com @Entity
* 3 - Selecione o nome das tabelas que essas entidades representação com @Table(name = "NomeDaTabela")
* 4 - As colunas serão os atributos das entidades
* 5 - Caso tenha dúvidas sobre quais anotações usar nesta etapa, acesse: [Outros Relacionamentos](/teoria/OutrosRelacionamentos.md)


---
## JPA Util

> Para detalhes, acesse: [JPA Util](/org/studies/JPAUtil/CreateEntityManeger.java)

Crie a classe JPA Util para criar e devolver a EntityManager

```txt
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
