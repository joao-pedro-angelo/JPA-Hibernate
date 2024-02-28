## Tipagem das colunas

Quando definimos uma entidade em um sistema Java com JPA, os atributos dessa entidade são mapeados para colunas no banco de dados. Geralmente, os tipos primitivos e alguns tipos especiais, como `LocalDate`, são mapeados automaticamente sem necessidade de configuração extra. No entanto, para tipos mais complexos, como enumeradores, é necessário realizar uma configuração adicional utilizando anotações.

Por exemplo, ao lidar com enumeradores, podemos utilizar a anotação `@Enumerated` para indicar à JPA como o mapeamento deve ser realizado. Veja um exemplo:

```java
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Enumerated(EnumType.STRING)
private AlgumEnum meuEnum;
```

Dessa forma, a JPA saberá que o atributo `meuEnum` deve ser mapeado para uma coluna do tipo String no banco de dados.

---
## Relacionamento entre tabelas

Quando lidamos com mais de uma tabela em nosso sistema, é comum que essas tabelas se relacionem entre si. Isso é feito por meio de relacionamentos, que podem ser mapeados utilizando anotações na JPA.

### Many-to-One e One-to-Many

O relacionamento Many-to-One representa a relação de muitos para um entre entidades. Por exemplo, muitas consultas podem estar associadas a uma única pessoa. Veja um exemplo de mapeamento:

```java
@Entity(name = "Consulta")
@Table(name = "consultas")
public class Consulta {

  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa pessoa;
}
```

Para estabelecer um relacionamento bidirecional, onde a entidade `Pessoa` possui uma lista de consultas, precisamos mapear o lado oposto do relacionamento:

```java
@Entity(name = "Pessoa")
@Table(name = "pessoas")
public class Pessoa {

  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy="pessoa")
  private List<Consulta> consultas;
}
```

### Many-to-Many

Para relacionamentos Many-to-Many, onde uma entidade possui múltiplas associações com outra entidade e vice-versa, é necessário um tratamento especial de mapeamento. Para mais detalhes sobre esse tipo de relacionamento, consulte a documentação ou este [artigo](https://www.devmedia.com.br/manytomany-hibernate-variacoes-unidirecional-e-bidirecional/29535).

### One-to-One

Relacionamentos One-to-One representam a relação onde uma entidade está associada a exatamente uma instância de outra entidade. Para mais informações sobre esse tipo de relacionamento, consulte este [artigo](https://atitudereflexiva.wordpress.com/2017/12/04/a-melhor-forma-de-mapear-um-relacionamento-onetoone-com-jpa/).

---
### Cuidado com a exceção de transação!

Ao lidar com relacionamentos complexos, como no exemplo onde um produto depende de uma categoria existente no banco de dados, é importante garantir que as transações sejam tratadas de forma adequada. Certifique-se de que a categoria associada a um produto já esteja persistida no banco de dados antes de tentar salvar o produto, evitando assim exceções de transação.
