package org.studies.validation;

import org.studies.exception.RegraDeNegocioException;

import java.math.BigDecimal;

/**
 * Validação dos atributos do Produto
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
public class ValidationProduct {

    /**
     * Valida nome do produto
     * @param nameProduct nome do produto
     */
    public static void validationName(String nameProduct){
        if (nameProduct.isBlank()) throw new RegraDeNegocioException("Nome inválido!");
    }

    /**
     * Valida valor do produto
     * @param valueProduct valor do produto
     */
    public static void validationValue(BigDecimal valueProduct){
        if (valueProduct == null) throw new RegraDeNegocioException("Valor inválido!");
    }

    /**
     * Valida quantidade do produto
     * @param quantity quantidade do produto
     */
    public static void validationQuantity(int quantity){
        if (quantity < 0) throw new RegraDeNegocioException("Quantidade inválida!");
    }
}
