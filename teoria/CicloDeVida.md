# Ciclo de Vida de uma entidade

> Os IDs gerados pelo banco de dados só começam a valer
> quando a entidade já está salva no banco de dados.

---
### Entidade instanciada 

```java
Produto produto = new Produto();
```

Nesse momento, produto é uma entidade instanciada.

Para a JPA, produto é um **TRANSIENT**.<br>
**TRANSIENTS** não são gerenciados pela JPA.


---
### .persist()

Ao invocar o método persist, passando o objeto instanciado (transient),
este objeto passa a ser gerenciado pela JPA e está no estado MANAGED. Ou seja,
durante aquela transação, a JPA está controlando o objeto transient.

Caso você não queira commitar a transação, mas quer sincronizar a entidade com o banco de dados,
há o método .flush()

Toda entidade que está sob o estado MANAGED, será inserida no banco de dados. 
É possível que uma entidade que já está salva volte ao estado MANAGED e seja salva novamente, com mudanças
que forem solicitas, sem alterar o ID.


---
### .close()

O objeto nesse momento deixa de ser controlado pela JPA e a transação foi finalizada.
Nesse momento, o objeto está no estado "detached", ou seja, um estado após a transação, já tem o ID
na base de dados.


---
### .merge()

Este método pega uma entidade que está no estado detached, ou seja, já está salva no BD e passa
esta entidade para o estado managed. 

Para que esse método funcione corretamente, é necessário que as entidades
possuam o construtor default.

```txt
Produto produtoManaged = em.merge(produtoDetached);
produtoManaged.setNome("Outro nome");
em.flush();
```

Recuperei a entidade, alterei o nome e atualizeo o banco de dados.


---
