package org.studies.validation;

import org.studies.exception.RegraDeNegocioException;

/**
 * Valida informações referentes à Categoria
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
public class ValidationCategory {

    /**
     * Valida nome da Categoria
     * @param name Nome da categoria
     */
    public static void validationCategory(String name){
        if (name.isBlank()) throw new RegraDeNegocioException("Nome de categoria inválido!");
    }
}
