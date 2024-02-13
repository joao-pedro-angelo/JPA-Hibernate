package org.studies.validation;

import org.studies.exception.RegraDeNegocioException;

public class ValidationCategory {

    public static void validationCategory(String name){
        if (name.isBlank()) throw new RegraDeNegocioException("Nome de categoria inválido!");
    }
}
