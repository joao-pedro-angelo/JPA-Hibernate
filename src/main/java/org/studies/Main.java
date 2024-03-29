/*
 * Classe responsável por interagir com o usuário, oferecendo opções para manipular produtos e categorias.
 * Utiliza serviços e DAOs para interagir com o banco de dados.
 * @author carneiroangelojoaopedro@gmail.com
 */
package org.studies;

import org.studies.JPAUtil.CreateEntityManager;
import org.studies.daos.CategoryDAO;
import org.studies.daos.ProductDAO;
import org.studies.entities.Category;
import org.studies.entities.Product;
import org.studies.exception.RegraDeNegocioException;
import org.studies.services.ServiceCategory;
import org.studies.services.ServiceProduct;
import org.studies.validation.ValidationCategory;
import org.studies.validation.ValidationProduct;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    // Objeto Scanner para entrada de dados do usuário
    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    // EntityManager para operações no banco de dados
    private static final EntityManager entityManager = CreateEntityManager.createEntityManager();
    // Serviço para operações relacionadas a categorias
    private static final ServiceCategory serviceCategory = new ServiceCategory(new CategoryDAO(entityManager), entityManager);
    // Serviço para operações relacionadas a produtos
    private static final ServiceProduct serviceProduct = new ServiceProduct(new ProductDAO(entityManager), entityManager);

    /**
     * Método principal para controlar as ações do usuário.
     * Oferece um menu interativo para o usuário realizar operações no banco de dados.
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        int opcao = exibirMenu();
        while (opcao != 0){
            try{
                switch (opcao){
                    case 1:
                        criarCategoria();
                        break;
                    case 2:
                        criarProduto();
                        break;
                    case 3:
                        removerProduto();
                        break;
                    case 4:
                        exibirCategoria();
                        break;
                    case 5:
                        exibirProduto();
                        break;
                    case 6:
                        atualizarQuantidadeProduto();
                        break;
                    case 7:
                        atualizarPrecoProduto();
                        break;
                    case 8:
                        listarTodosProdutos();
                        break;
                    case 9:
                        listarTodasCategorias();
                        break;
                }
            } catch (RegraDeNegocioException e){
                System.out.println("Erro: " + e.getMessage());
            }
            opcao = exibirMenu();
        }
        serviceProduct.closeEntityManager();
        serviceCategory.closeEntityManager();
        System.out.println("Finalizando aplicação...");
    }

    /**
     * Exibe todos os produtos cadastrados na base de dados.
     * Caso não haja produtos cadastrados, a lista será vazia.
     */
    private static void listarTodosProdutos(){
        System.out.println("\n");
        System.out.println("Listar todos os produtos: ");
        System.out.println(serviceProduct.readAllProducts());
    }

    /**
     * Exibe todas as categorias cadastradas na base de dados.
     * Caso não haja categorias cadastradas, a lista será vazia.
     */
    private static void listarTodasCategorias(){
        System.out.println("\n");
        System.out.println("Listar todas as categorias: ");
        System.out.println(serviceCategory.readAllCategories());
    }

    /**
     * Atualiza o preço de um produto existente na base de dados.
     * Caso o produto não exista, uma mensagem de erro será exibida.
     * Caso o valor informado seja inválido, uma mensagem de erro será exibida.
     */
    private static void atualizarPrecoProduto(){
        System.out.println("\n");
        System.out.println("ATUALIZAR PREÇO - PRODUTO");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        Product product = serviceProduct.readProduct(nameProduct);

        System.out.println("Preço do produto: ");
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        ValidationProduct.validationValue(value);

        product.setValue(value);
        serviceProduct.updateProduct(product);

        System.out.println("Preço atualizado!");
    }

    /**
     * Atualiza a quantidade de um produto existente na base de dados.
     * Caso o produto não exista, uma mensagem de erro será exibida.
     * Caso a quantidade informada seja inválida, uma mensagem de erro será exibida.
     */
    private static void atualizarQuantidadeProduto() {
        System.out.println("\n");
        System.out.println("ATUALIZAR QUANTIDADE - PRODUTO");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);
        Product product = serviceProduct.readProduct(nameProduct);

        System.out.println("Nova quantidade: ");
        try{
            int quantidade = Integer.parseInt(scanner.nextLine());
            ValidationProduct.validationQuantity(quantidade);
            product.setQuantity(quantidade);
            serviceProduct.updateProduct(product);
        } catch (NumberFormatException e){
            System.out.println("Quantidade inválida!");
        }

        System.out.println("Fim da operação. Retornando ao menu principal...");
    }

    /**
     * Exibe as informações de um produto.
     * Caso o produto não exista, uma mensagem de erro será exibida.
     */
    private static void exibirProduto() {
        System.out.println("\n");
        System.out.println("EXIBIR PRODUTO: ");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        System.out.println(serviceProduct.readProduct(nameProduct));
    }

    /**
     * Exibe as informações de uma categoria.
     * Caso a categoria não exista, uma mensagem de erro será exibida.
     */
    private static void exibirCategoria() {
        System.out.println("\n");
        System.out.println("EXIBIR CATEGORIA: ");

        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();
        ValidationCategory.validationCategory(categoryName);

        System.out.println(serviceCategory.readCategory(categoryName));
    }

    /**
     * Remove um produto da base de dados.
     * Caso o produto não exista, uma mensagem de erro será exibida.
     */
    private static void removerProduto() {
        System.out.println("\n");
        System.out.println("REMOVER PRODUTO: ");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        Product product = serviceProduct.readProduct(nameProduct);
        serviceProduct.removeProduct(product);

        System.out.println("Remoção bem sucedida!");
    }

    /**
     * Cria um novo produto na base de dados.
     * Caso a categoria informada não exista, uma mensagem de erro será exibida.
     * Caso algum dado informado seja inválido, uma mensagem de erro será exibida.
     */
    private static void criarProduto() {
        System.out.println("\n");
        System.out.println("CRIAR PRODUTO: ");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        System.out.println("Quantidade no estoque: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        ValidationProduct.validationQuantity(quantity);

        System.out.println("Descrição do produto: ");
        String description = scanner.nextLine();

        System.out.println("Valor: ");
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        ValidationProduct.validationValue(value);

        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();
        ValidationCategory.validationCategory(categoryName);

        Category category = serviceCategory.readCategory(categoryName);

        Product product = new Product(nameProduct, quantity, description, value, category);
        serviceProduct.createProduct(product);

        System.out.println("Produto criado com sucesso!");
    }

    /**
     * Cria uma nova categoria na base de dados.
     * Caso o nome da categoria seja inválido, uma mensagem de erro será exibida.
     */
    private static void criarCategoria() {
        System.out.println("\n");
        System.out.println("CRIAR CATEGORIA: ");

        System.out.println("Nome da categoria: ");
        String nameCategory = scanner.nextLine();
        ValidationCategory.validationCategory(nameCategory);

        Category category = new Category(nameCategory);
        serviceCategory.createCategory(category);

        System.out.println("Categoria criada com sucesso!");
    }

    /**
     * Exibe o menu de opções para o usuário.
     * @return A escolha do usuário.
     */
    private static int exibirMenu(){
        System.out.println("""
                CLASSIC BANK - ESCOLHA UMA OPÇÃO:
                0 - Sair
                1 - Criar categoria
                2 - Criar produto
                3 - Remover produto
                4 - Exibir categoria
                5 - Exibir produto
                6 - Atualizar quantidade - produto
                7 - Atualizar preço - produto
                8 - Listar todos os produtos
                9 - Listar todas as categorias
                """);
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Entrada inválida...");
            return 0;
        }
    }

}
