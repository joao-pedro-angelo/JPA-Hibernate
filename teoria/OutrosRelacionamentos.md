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

### Exemplos de relacionamentos: 
#### ManyToOne e OneToMany
É o relacionamento de muitos para um, por exemplo:
```txt
@Entity(name = "consulta")
@Table(name = "consultas")
public class Consulta{
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa;
}
```
Muitas consultas para uma pessoa. Uma pessoa pode ter muitas consultas.

Quando o relacionamento é unidirecional, então apenas um dos lados da relação tem a referência para o outro.
Porém, em relacionamentos @ManyToOne, é comum mapear o outro lado como @OneToMany, ou seja, temos um relacionamento bidirecional.

No caso, na classe Pessoa, teríamos:
```txt
@Entity(name = "pessoa")
@Table(name = "pessoas")
public class Pessoa{

  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy="pessoa")
  private List<Consulta> consultas;
}
```

> Se liga nas anotações! Em um relacionamento bidirecional, do tipo @ManyToOne e @OneToMany, é importante dizer para a JPA
> qual é o lado dominante da relação. O lado dominante é sempre o do @ManyToOne (Muitos dominam um). Para dizer que este lado é dominante, basta inserir a anotação @JoinColumn(name = "..."), especificando a chave estrangeira do dominado. Já na classe dominada, insira o atributo "mappedBy=...".


#### ManyToMany e ManyToMany
> Sobre este relacionamento:<br> https://www.devmedia.com.br/manytomany-hibernate-variacoes-unidirecional-e-bidirecional/29535


#### OnetoOne e OneToOne
> Sobre:<br> https://atitudereflexiva.wordpress.com/2017/12/04/a-melhor-forma-de-mapear-um-relacionamento-onetoone-com-jpa/


---
#### Cuidado com a exceção de transação!

Em nosso sistema, um produto tem uma categoria. Para salvarmos um produto na base de dados, é necessário que a categoria dele
já esteja salva no banco de dados. Produto possui/contém uma categoria. Logo, a categoria tem que existir antes do produto.
