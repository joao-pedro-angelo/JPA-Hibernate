package org.studies;

import org.studies.JPAUtil.CreateEntityManager;
import org.studies.daos.CategoryDAO;
import org.studies.daos.ProductDAO;
import org.studies.entities.Category;
import org.studies.entities.Product;
import org.studies.exception.RegraDeNegocioException;
import org.studies.services.ServiceCategory;
import org.studies.services.ServiceProduct;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static EntityManager entityManager = CreateEntityManager.createEntityManager();
    private static final ServiceCategory serviceCategory = new ServiceCategory(new CategoryDAO(entityManager), entityManager);
    private static final ServiceProduct serviceProduct = new ServiceProduct(new ProductDAO(entityManager), entityManager);

    public static void main(String[] args) {
        int opcao = exibirMenu();
        while (opcao != 9){
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
                        atualizarProduto();
                        break;
                }
            } catch (RegraDeNegocioException e){
                System.out.println("Erro: " + e.getMessage());
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizando aplicação...");
    }

    private static void atualizarProduto() {
    }

    private static void atualizarCategoria() {
        System.out.println("\n");
        System.out.println("ATUALIZAR CATEGORIA: ");
        System.out.println("Nome atual da categoria: ");
        String actualName = scanner.nextLine();
        System.out.println("Novo nome: ");
        String newName = scanner.nextLine();

        Category category = serviceCategory.readCategory(actualName);
        category.setName(newName);
        serviceCategory.updateCategory(category);
    }

    private static void exibirProduto() {
    }

    private static void exibirCategoria() {
        System.out.println("\n");
        System.out.println("EXIBIR CATEGORIA: ");
        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();

        System.out.println(serviceCategory.readCategory(categoryName));
    }

    private static void removerProduto() {
    }

    private static void removerCategoria() {
        System.out.println("\n");
        System.out.println("REMOVER CATEGORIA: ");
        System.out.println("Nome da categoria: ");
        String name = scanner.nextLine();

        Category category = serviceCategory.readCategory(name);
        serviceCategory.removeCategory(category);

        System.out.println("Remoção bem sucedida!");
    }

    private static void criarProduto() {
        System.out.println("\n");
        System.out.println("CRIAR PRODUTO: ");
        System.out.println("Nome do produto: ");
        String nameProduct = scanner.nextLine();
        System.out.println("Descrição do produto: ");
        String description = scanner.nextLine();
        System.out.println("Valor: ");
        BigDecimal value = BigDecimal.valueOf(Integer.parseInt(scanner.nextLine()));
        System.out.println("Nome da categoria: ");
        String categoryName = scanner.nextLine();
        Category category = serviceCategory.readCategory(categoryName);

        Product product = new Product(nameProduct, description, value, category);
        serviceProduct.createProduct(product);

        System.out.println("Produto criado com sucesso!");
    }

    private static void criarCategoria() {
        System.out.println("\n");
        System.out.println("CRIAR CATEGORIA: ");
        System.out.println("Nome da categoria: ");
        String nameCategory = scanner.nextLine();

        Category category = new Category(nameCategory);
        serviceCategory.createCategory(category);

        System.out.println("Categoria criada com sucesso!");
    }

    private static int exibirMenu(){
        System.out.println("""
                CLASSIC BANK - ESCOLHA UMA OPÇÃO:
                1 - Criar categoria
                2 - Criar produto
                3 - Remover categoria
                4 - Remover produto
                5 - Exibir categoria
                6 - Exibir produto
                7 - Atualizar categoria
                8 - Atualizar produto
                9 - Sair
                """);
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Entrada inválida...");
            return 9;
        }
    }

}