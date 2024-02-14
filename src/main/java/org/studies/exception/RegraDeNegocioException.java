package org.studies.exception;

/**
 * Exceção - RegraDeNegócio
 * Invocada quando alguma regra de negócio for quebrada
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
public class RegraDeNegocioException extends RuntimeException {

    /**
     * Construtor
     * @param msg Mensagem de erro
     */
    public RegraDeNegocioException(String msg){
        super(msg);
    }
}
