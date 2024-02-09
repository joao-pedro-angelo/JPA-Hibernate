# Mapeamento Objeto Relacional

Suponha que nosso sistema represente um e-commerce e precisamos de uma tabela
para armazenar os produtos desta loja.

Precisamos de uma tabela nomeada como **Produtos**.
Esta tabela terá uma coluna ID, que será um Integer que identificará os elementos da tabela.
Além disso, terá o atributo **Nome**, que será uma String (varchar), uma **Descrição** - String (varchar) e um
**Preço** - Double (decimal).

Como criar esta tabela com a JPA ?

Se estivéssemos com a API JDBC pura, precisaríamos de um código verboso e
utilizaríamos a linguagem de consulta estruturada (SQL).

Com a JPA, basta criar uma classe para representar esta entidade.

Veja a classe:

```java
// Importe da especificação, não da implementação

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Produtos")
public class Produto {
    // As colunas da tabela serão os atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String nome;
    
    @Column(name = "desc") //Mudando o nome da coluna
    private String descricao;
    
    private BigDecimal preco;
}
```

Utilizamos as anotações para indicar à JPA o que ela deve fazer e como o
mapeamento deve ser realizado.

O que foi feito acima é o que é conhecido como Mapeamento Objeto-Relacional (ORM).

Veja a persistência sendo efetuada em:
[Persistindo Entidade](/src/main/java/org/studies/Main.java)