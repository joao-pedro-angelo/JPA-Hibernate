# Ciclo de Vida de uma Entidade

> Os IDs gerados pelo banco de dados só começam a valer quando a entidade já está salva no banco de dados.


---
### Entidade Instanciada 

```java
Produto produto = new Produto();
```

Nesse momento, o objeto `produto` é uma entidade instanciada.

Para a JPA, o objeto `produto` está em estado **TRANSIENT**.<br>
Objetos TRANSIENT não são gerenciados pela JPA.


---
### Método `.persist()`

Ao invocar o método `persist`, passando o objeto instanciado (transient), este objeto passa a ser gerenciado pela JPA e está no estado MANAGED. Ou seja, durante aquela transação, a JPA está controlando o objeto transient.

Caso você não queira cometer a transação, mas queira sincronizar a entidade com o banco de dados, há o método `.flush()`.

Toda entidade que está sob o estado MANAGED será inserida no banco de dados. É possível que uma entidade que já está salva volte ao estado MANAGED e seja salva novamente, com mudanças que forem solicitadas, sem alterar o ID.


---
### Método `.close()`

O objeto nesse momento deixa de ser controlado pela JPA e a transação foi finalizada. Nesse momento, o objeto está no estado "detached", ou seja, um estado após a transação, já tem o ID na base de dados.


---
### Método `.merge()`

Este método assegura que o estado do objeto é Managed, ou seja, está sendo manuseado pela JPA.

Para que esse método funcione corretamente, é necessário que as entidades possuam o construtor padrão.

```java
Produto produtoManaged = em.merge(produtoDetached);
produtoManaged.setNome("Outro nome");
em.flush();
```

Recuperei a entidade, alterei o nome e atualizei o banco de dados.


---
### Método `.find()`

Este método pode ser utilizado para encontrar determinada entidade, passando o seu ID. O objeto recuperado estará no estado Managed, ou seja, sob a ação da JPA. Muito útil para atualizar objetos.


---
### Exemplo de Código

```java
em.getTransaction().begin();
Produto produto = em.find(Produto.class, 1L);
produto.setDescricao("Teste 1");
em.flush();
produto.setDescricao("Teste 2");
em.merge(produto);
produto.setDescricao("Teste 3");
em.getTransaction().commit();
em.close();
```

A entidade produto será atualizada no banco de dados com a descrição "Teste 3".

No código anterior, o merge acabou sendo indiferente, pois a entidade já estava no estado Managed.


---
### Exclusão de uma Entidade

Basta usar o método `.remove()` na entidade que estiver sob o estado managed.
