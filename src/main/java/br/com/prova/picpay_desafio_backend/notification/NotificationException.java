package br.com.prova.picpay_desafio_backend.notification;

/**
 * Exceção lançada quando ocorre um erro ao notificar uma transação.
 */
public class NotificationException extends RuntimeException {
    
	/**
     * Construtor que cria uma nova exceção com a mensagem especificada.
     *
     * @param message a mensagem de detalhe da exceção.
     */
	public NotificationException(String message) {
      super(message);
    }
  }