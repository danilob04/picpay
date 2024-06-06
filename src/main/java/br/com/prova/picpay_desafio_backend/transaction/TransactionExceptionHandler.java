package br.com.prova.picpay_desafio_backend.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe que trata exceções relacionadas a transações.
 */
@ControllerAdvice
public class TransactionExceptionHandler {


    /**
     * Manipula exceções do tipo InvalidTransactionException.
     *
     * @param exception a exceção lançada.
     * @return uma resposta HTTP com o status de erro e a mensagem da exceção.
     */
  @ExceptionHandler(InvalidTransactionException.class)
  public ResponseEntity<Object> handle(InvalidTransactionException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }
}
