package br.com.prova.picpay_desafio_backend.authorization;

/**
 * Exceção lançada quando uma transação não é autorizada.
 */
public class UnauthorizedTransactionException extends RuntimeException {
	/**
     * Construtor que cria uma nova exceção com a mensagem especificada.
     *
     * @param message a mensagem de detalhe da exceção.
     */
	public UnauthorizedTransactionException(String message) {
      super(message);
    }
  }
