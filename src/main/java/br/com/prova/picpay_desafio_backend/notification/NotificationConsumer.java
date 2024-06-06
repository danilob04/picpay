package br.com.prova.picpay_desafio_backend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.prova.picpay_desafio_backend.transaction.Transaction;


/**
 * Serviço responsável por consumir notificações de transações.
 */
@Service
public class NotificationConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
  private RestClient restClient;

  /**
   * Construtor que inicializa o cliente REST com uma URL base.
   *
   * @param builder o construtor do cliente REST.
   */
  public NotificationConsumer(RestClient.Builder builder) {
    this.restClient = builder.baseUrl(
        "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
        .build();
  }
  /**
   * Recebe notificações de transações do tópico Kafka.
   *
   * @param transaction a transação a ser notificada.
   * @throws NotificationException se houver um erro ao notificar a transação.
   */
  @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
  public void receiveNotification(Transaction transaction) {
    LOGGER.info("notifying transaction {}...", transaction);

    var response = restClient.get().retrieve().toEntity(Notification.class);

    if (response.getStatusCode().isError() || !response.getBody().message())
      throw new NotificationException("Error notifying transaction " + transaction);

    LOGGER.info("notification has been sent {}...", response.getBody());
  }
}
