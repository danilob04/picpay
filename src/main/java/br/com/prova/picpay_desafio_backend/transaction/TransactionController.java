package br.com.prova.picpay_desafio_backend.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador responsável por lidar com as operações relacionadas às transações.
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {
  private final TransactionService transactionService;

  /**
   * Construtor que inicializa o serviço de transações.
   *
   * @param transactionService o serviço de transações.
   */
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  /**
   * Retorna uma lista de transações.
   *
   * @return a lista de transações.
   */
  @GetMapping
  public List<Transaction> list() {
    return transactionService.list();
  }
  /**
   * Cria uma nova transação.
   *
   * @param transaction a transação a ser criada.
   * @return a transação criada.
   */
  @PostMapping
  public Transaction createTransaction(@RequestBody Transaction transaction) {
    return transactionService.create(transaction);
  }

}