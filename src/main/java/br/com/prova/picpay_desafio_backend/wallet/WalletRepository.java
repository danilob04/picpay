package br.com.prova.picpay_desafio_backend.wallet;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface que define um repositório para carteiras.
 */
public interface WalletRepository extends CrudRepository<Wallet, Long>{
}