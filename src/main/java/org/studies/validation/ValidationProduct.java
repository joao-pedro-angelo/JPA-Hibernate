package org.studies.validation;

import org.studies.entities.Category;
import org.studies.exception.RegraDeNegocioException;

import java.math.BigDecimal;

public class ValidationProduct {

    public static void validationName(String nameProduct){
        if (nameProduct.isBlank()) throw new RegraDeNegocioException("Nome inválido!");
    }

    public static void validationValue(BigDecimal valueProduct){
        if (valueProduct == null) throw new RegraDeNegocioException("Valor inválido!");
    }

    public static void validationQuantity(int quantity){
        if (quantity < 0) throw new RegraDeNegocioException("Quantidade inválida!");
    }
}
