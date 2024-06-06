package br.com.prova.picpay_desafio_backend.transaction;

import org.springframework.data.repository.ListCrudRepository;

/**
 * Interface que define um repositório de transações.
 */
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
