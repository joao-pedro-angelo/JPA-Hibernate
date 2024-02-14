# Outros relacionamentos

---
## Tipagem das colunas

Já sabemos que, ao criar uma entidade, esta será mapeada para uma tabela
no banco de dados. As colunas serão do tipo de cada um dos atributos. Porém, e quando
o atributo não for primitivo nem derivado de um primitivo?

Os tipos primitivos, seus derivados e alguns tipos especiais como LocalDate são mapeados para
colunas sem necessidade de configuração extra. Porém, outros tipos, como enumeradores, necessitam de 
uma configuração por anotação. Para enumeradores, basta usar:

```java
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Enumerated(EnumType.STRING)
private AlgumEnum meuEnum;
```

Assim, a JPA saberá que deve mapear aquele atributo para uma coluna
do tipo String e a coluna será nomeada como "AlgumEnum", assumindo o valor de "meuEnum".


---
## Relacionamento entre tabelas

Quando temos mais de uma tabela em nosso sistema, ou seja, mais de uma entidade, elas podem se relacionar 
por meio da composição e serem facilmente mapeadas por meio de anotações.

Há anotações para relacionamentos de um para um, **@OneToOne**, muitos para um, **@ManyToOne**, e assim por diante!

### Exemplo de relacionamento: 
#### OneToMany
O relacionamento OneToMany indica que uma entidade está associada a várias instâncias de outra entidade. Por exemplo, uma categoria pode ter vários produtos. Nesse caso, a categoria seria a entidade "um" e os produtos seriam a entidade "muitos".


---
#### Cuidado com a exceção de transação!

Em nosso sistema, um produto tem uma categoria. Para salvarmos um produto na base de dados, é necessário que a categoria dele
já esteja salva no banco de dados. Produto possui/contém uma categoria. Logo, a categoria tem que existir antes do produto.
