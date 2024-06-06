package br.com.prova.picpay_desafio_backend.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prova.picpay_desafio_backend.authorization.AuthorizerService;
import br.com.prova.picpay_desafio_backend.notification.NotificationService;
import br.com.prova.picpay_desafio_backend.wallet.WalletRepository;
import br.com.prova.picpay_desafio_backend.wallet.WalletType;

/**
 * Serviço responsável por lidar com operações relacionadas a transações.
 */
@Service
public class TransactionService {
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
  private final TransactionRepository transactionRepository;
  private final WalletRepository walletRepository;
  private final AuthorizerService authorizerService;
  private final NotificationService notificationService;

  
  /**
   * Construtor que inicializa os repositórios e serviços necessários.
   *
   * @param transactionRepository o repositório de transações.
   * @param walletRepository o repositório de carteiras.
   * @param authorizerService o serviço de autorização.
   * @param notificationService o serviço de notificação.
   */
  public TransactionService(TransactionRepository transactionRepository,
      WalletRepository walletRepository, AuthorizerService authorizerService,
      NotificationService notificationService) {
    this.transactionRepository = transactionRepository;
    this.walletRepository = walletRepository;
    this.authorizerService = authorizerService;
    this.notificationService = notificationService;
  }

  
  /**
   * Cria uma nova transação.
   *
   * @param transaction a transação a ser criada.
   * @return a transação criada.
   */
  @Transactional
  public Transaction create(Transaction transaction) {
    validate(transaction);

    var newTransaction = transactionRepository.save(transaction);

    var walletPayer = walletRepository.findById(transaction.payer()).get();
    var walletPayee = walletRepository.findById(transaction.payee()).get();
    walletRepository.save(walletPayer.debit(transaction.value()));
    walletRepository.save(walletPayee.credit(transaction.value()));

    authorizerService.authorize(transaction);
    notificationService.notify(newTransaction);

    return newTransaction;
  }

  /**
   * Valida uma transação.
   *
   * @param transaction a transação a ser validada.
   * @throws InvalidTransactionException se a transação não for válida.
   */
  private void validate(Transaction transaction) {
    LOGGER.info("validating transaction {}...", transaction);

    walletRepository.findById(transaction.payee())
        .map(payee -> walletRepository.findById(transaction.payer())
            .map(
                payer -> payer.type() == WalletType.COMUM.getValue() &&
                    payer.balance().compareTo(transaction.value()) >= 0 &&
                    !payer.id().equals(transaction.payee()) ? true : null)
            .orElseThrow(() -> new InvalidTransactionException(
                "Invalid transaction - " + transaction)))
        .orElseThrow(() -> new InvalidTransactionException(
            "Invalid transaction - " + transaction));
  }

  /**
   * Retorna uma lista de transações.
   *
   * @return a lista de transações.
   */
  public List<Transaction> list() {
    return transactionRepository.findAll();
  }
}
