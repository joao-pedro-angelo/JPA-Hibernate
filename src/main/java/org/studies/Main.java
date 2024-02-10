package org.studies;

import org.studies.JPAUtil.CreateEntityManager;
import org.studies.daos.CategoryDAO;
import org.studies.daos.ProductDAO;
import org.studies.services.ServiceCategory;
import org.studies.services.ServiceProduct;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static EntityManager entityManager = CreateEntityManager.createEntityManager();
    private static final ServiceCategory serviceCategory = new ServiceCategory(new CategoryDAO(entityManager), entityManager);
    private static final ServiceProduct serviceProduct = new ServiceProduct(new ProductDAO(entityManager), entityManager);

    public static void main(String[] args) {

    }

}