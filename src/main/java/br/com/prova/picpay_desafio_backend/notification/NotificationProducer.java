package br.com.prova.picpay_desafio_backend.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.prova.picpay_desafio_backend.transaction.Transaction;

/**
 * Serviço responsável por produzir notificações de transações e enviá-las para um tópico Kafka.
 */
@Service
public class NotificationProducer {
  private final KafkaTemplate<String, Transaction> kafkaTemplate;

  
  /**
   * Construtor que inicializa o KafkaTemplate.
   *
   * @param kafkaTemplate o template do Kafka para enviar mensagens.
   */
  public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  
  /**
   * Envia uma notificação de transação para o tópico Kafka.
   *
   * @param transaction a transação a ser notificada.
   */
  public void sendNotification(Transaction transaction) {
    kafkaTemplate.send("transaction-notification", transaction);
  }
}