package br.com.prova.picpay_desafio_backend.transaction;

/**
 * Exceção lançada quando uma transação é inválida.
 */
public class InvalidTransactionException extends RuntimeException{
   
	/**
     * Construtor que cria uma nova exceção com a mensagem especificada.
     *
     * @param message a mensagem de detalhe da exceção.
     */
	public InvalidTransactionException(String message) {
        super(message);
    }
}
