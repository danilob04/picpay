package br.com.prova.picpay_desafio_backend.notification;


/**
 * Um registro que representa uma notificação.
 *
 * @param message um booleano que indica o status da mensagem.
 */
public record Notification(
    boolean message) {

}
