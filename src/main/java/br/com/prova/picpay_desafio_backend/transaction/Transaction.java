package br.com.prova.picpay_desafio_backend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Um registro que representa uma transação.
 */
@Table("TRANSACTIONS")
public record Transaction(
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    @CreatedDate LocalDateTime createdAt) {
 
	/**
     * Construtor que ajusta o valor da transação para duas casas decimais.
     */
        public Transaction {
    value = value.setScale(2);
  }

        /**
         * Método estático para criar uma transação. (Método não implementado)
         *
         * @param l o ID da transação.
         * @param m o ID do pagador.
         * @param n o ID do recebedor.
         * @param bigDecimal o valor da transação.
         * @param now a data e hora de criação da transação.
         * @return a transação criada.
         * @throws UnsupportedOperationException se o método não estiver implementado.
         */
        public static Transaction createTransaction(long l, long m, long n, BigDecimal bigDecimal, LocalDateTime now) {
            throw new UnsupportedOperationException("Unimplemented method 'createTransaction'");
        }
}

