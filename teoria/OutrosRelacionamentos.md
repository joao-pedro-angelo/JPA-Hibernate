# Outros relacionamentos


---
## Tipagem das colunas

Já sabemos que, ao criar uma entidade, esta entidade será mapeada para uma tabela
no banco de dados. As colunas serão do tipo de cada um dos atributos. Porém, e quando
algum atributo for de um tipo que não seja primitivo ou derivado de um primitivo?

Os tipos primitivos e seus derivados e alguns tipos especiais como LocalDate são mapeados para
colunas sem necessidade de configuração extra. Porém, outros tipos, como enumeradores, necessitam de 
uma configuração por anotação. Para enumeradores, basta usar:

```java
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Enumerated(EnumType.STRING)
private AlgumEnum meuEnum;
```

Logo, a JPA saberá que deve mapear aquele atributo para uma coluna
do tipo String e a coluna será nomeada como "AlgumEnum", sendo um dos valores o valor de "meuEnum".


---
## Relacionamento entre tabelas

Quando temos mais de uma tabela em nosso sistema, ou seja, mais de uma entidade, elas podem se relacionar 
por meio da composição e serem facilmente mapeadas por meio de anotações.

Há anotações para relacionamentos de um para um, @OneToOne. Há para muitos para um, @ManyToOne... e assim por diante!


#### Cuidado com a exceção de transação!
Em nosso sistema, um product tem uma category. Para salvarmos um product na base de dados, é necessário que a category dele
já esteja salva no banco de dados.
Produto possui/contém uma category. Logo, a category tem que existir antes do product.
