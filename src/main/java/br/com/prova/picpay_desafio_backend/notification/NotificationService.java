package br.com.prova.picpay_desafio_backend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.prova.picpay_desafio_backend.transaction.Transaction;


/**
 * Serviço responsável por gerenciar a notificação de transações.
 */
@Service
public class NotificationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
  private NotificationProducer notificationProducer;

  
  /**
   * Construtor que inicializa o produtor de notificações.
   *
   * @param notificationProducer o produtor de notificações.
   */
  public NotificationService(NotificationProducer notificationProducer) {
    this.notificationProducer = notificationProducer;
  }

  /**
   * Notifica uma transação.
   *
   * @param transaction a transação a ser notificada.
   */
  public void notify(Transaction transaction) {
    LOGGER.info("notifying transaction {}...", transaction);

    notificationProducer.sendNotification(transaction);
  }
}