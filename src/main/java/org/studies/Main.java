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

    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static final EntityManager entityManager = CreateEntityManager.createEntityManager();
    private static final ServiceCategory serviceCategory = new ServiceCategory(new CategoryDAO(entityManager), entityManager);
    private static final ServiceProduct serviceProduct = new ServiceProduct(new ProductDAO(entityManager), entityManager);

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
                        atualizarCategoria();
                        break;
                    case 8:
                        atualizarQuantidadeProduto();
                        break;
                    case 9:
                        atualizarNomeProduto();
                        break;
                    case 10:
                        atualizarPrecoProduto();
                        break;
                    case 11:
                        listarTodosProdutos();
                        break;
                    case 12:
                        listarTodasCategorias();
                        break;
                }
            } catch (RegraDeNegocioException e){
                System.out.println("Erro: " + e.getMessage());
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizando aplicação...");
    }

    private static void listarTodosProdutos(){
        System.out.println("\n");
        System.out.println("Listar todos os produtos: ");
        System.out.println(serviceProduct.readAllProducts());
    }

    private static void listarTodasCategorias(){
        System.out.println("\n");
        System.out.println("Listar todas as categorias: ");
        System.out.println(serviceCategory.readAllCategories());
    }

    private static void atualizarPrecoProduto(){
        System.out.println("\n");
        System.out.println("ATUALIZAR PREÇO - PRODUTO");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        Product product = serviceProduct.readProduct(nameProduct);

        System.out.println("Preço do produto: ");
        BigDecimal value = BigDecimal.valueOf(Integer.parseInt(scanner.next()));
        ValidationProduct.validationValue(value);

        product.setValue(value);
        serviceProduct.updateProduct(product);

        System.out.println("Preço atualizado!");
    }

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

    private static void atualizarCategoria() {
        System.out.println("\n");
        System.out.println("ATUALIZAR CATEGORIA: ");

        System.out.println("Nome atual da categoria: ");
        String actualName = scanner.nextLine();
        System.out.println("Novo nome: ");
        String newName = scanner.nextLine();
        ValidationCategory.validationCategory(newName);

        Category category = serviceCategory.readCategory(actualName);
        category.setName(newName);
        serviceCategory.updateCategory(category);

        System.out.println("Atualização feita!");
    }

    private static void exibirProduto() {
        System.out.println("\n");
        System.out.println("EXIBIR PRODUTO: ");

        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        ValidationProduct.validationName(nameProduct);

        System.out.println("Produto: ");
        System.out.println(serviceProduct.readProduct(nameProduct));
    }

    private static void exibirCategoria() {
        System.out.println("\n");
        System.out.println("EXIBIR CATEGORIA: ");

        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();
        ValidationCategory.validationCategory(categoryName);

        System.out.println("Categoria: ");
        System.out.println(serviceCategory.readCategory(categoryName));
    }

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
        BigDecimal value = BigDecimal.valueOf(Integer.parseInt(scanner.nextLine()));
        ValidationProduct.validationValue(value);

        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();
        ValidationCategory.validationCategory(categoryName);

        Category category = serviceCategory.readCategory(categoryName);

        Product product = new Product(nameProduct, quantity, description, value, category);
        serviceProduct.createProduct(product);

        System.out.println("Produto criado com sucesso!");
    }

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
                7 - Atualizar categoria
                8 - Atualizar quantidade - produto
                9 - Atualizar nome - produto
               10 - Atualizar preço - produto
               11 - Listar todos protudos
               12 - Listar todas categorias
                """);
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Entrada inválida...");
            return 0;
        }
    }

}