package br.com.prova.picpay_desafio_backend.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Um registro que representa uma carteira.
 */
@Table("WALLETS")
public record Wallet(
    @Id Long id,
    String fullName,
    Long cpf,
    String email,
    String password,
    int type,
    BigDecimal balance,
    @Version Long version) {

	 /**
     * Debita um valor da carteira.
     *
     * @param value o valor a ser debitado.
     * @return a carteira atualizada após o débito.
     */
  public Wallet debit(BigDecimal value) {
    return new Wallet(id, fullName, cpf, email, password, type,
        balance.subtract(value), version);
  }

  /**
   * Credita um valor na carteira.
   *
   * @param value o valor a ser creditado.
   * @return a carteira atualizada após o crédito.
   */
  public Wallet credit(BigDecimal value) {
    return new Wallet(id, fullName, cpf, email, password, type, balance.add(value), version);
  }
}
