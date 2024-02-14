/*
 * Classe de interação com o usuário.
 * Fornece opções para criar, deletar, atualizar e ler informações dos produtos e categorias.
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
                        removerCategoria();
                        break;
                    case 4:
                        removerProduto();
                        break;
                    case 5:
                        exibirCategoria();
                        break;
                    case 6:
                        exibirProduto();
                        break;
                    case 7:
                        atualizarQuantidadeProduto();
                        break;
                    case 8:
                        atualizarNomeProduto();
                        break;
                    case 9:
                        atualizarPrecoProduto();
                        break;
                    case 10:
                        listarTodosProdutos();
                        break;
                    case 11:
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
     */
    private static void listarTodosProdutos(){
        System.out.println("\n");
        System.out.println("Listar todos os produtos: ");
        System.out.println(serviceProduct.readAllProducts());
    }

    /**
     * Exibe todas as categorias cadastradas na base de dados.
     */
    private static void listarTodasCategorias(){
        System.out.println("\n");
        System.out.println("Listar todas as categorias: ");
        System.out.println(serviceCategory.readAllCategories());
    }

    /**
     * Atualiza o preço de um produto existente na base de dados.
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
     * Atualiza o nome de um produto existente na base de dados.
     */
    private static void atualizarNomeProduto(){
        System.out.println("\n");
        System.out.println("ATUALIZAR NOME - PRODUTO");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        Product product = serviceProduct.readProduct(nameProduct);

        System.out.println("Novo nome: ");
        String name = scanner.nextLine();
        ValidationProduct.validationName(name);

        product.setName(name);
        serviceProduct.updateProduct(product);
        System.out.println("Fim da operação!");
    }

    /**
     * Atualiza a quantidade de um produto existente na base de dados.
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
     */
    private static void exibirProduto() {
        System.out.println("\n");
        System.out.println("EXIBIR PRODUTO: ");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        System.out.println("Produto: ");
        System.out.println(serviceProduct.readProduct(nameProduct));
    }

    /**
     * Exibe as informações de uma categoria.
     */
    private static void exibirCategoria() {
        System.out.println("\n");
        System.out.println("EXIBIR CATEGORIA: ");

        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();
        ValidationCategory.validationCategory(categoryName);

        System.out.println("Categoria: ");
        System.out.println(serviceCategory.readCategory(categoryName));
    }

    /**
     * Remove um produto da base de dados.
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
     * Remove uma categoria da base de dados.
     */
    private static void removerCategoria() {
        System.out.println("\n");
        System.out.println("REMOVER CATEGORIA: ");

        System.out.println("Nome da categoria: ");
        String name = scanner.nextLine();
        ValidationCategory.validationCategory(name);

        Category category = serviceCategory.readCategory(name);
        serviceCategory.removeCategory(category);

        System.out.println("Remoção bem sucedida!");
    }

    /**
     * Cria um novo produto na base de dados.
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
                3 - Remover categoria
                4 - Remover produto
                5 - Exibir categoria
                6 - Exibir produto
                7 - Atualizar quantidade - produto
                8 - Atualizar nome - produto
                9 - Atualizar preço - produto
               10 - Listar todos os produtos
               11 - Listar todas as categorias
                """);
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Entrada inválida...");
            return 0;
        }
    }

}
