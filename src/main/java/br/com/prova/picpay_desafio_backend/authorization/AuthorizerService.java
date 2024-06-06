package br.com.prova.picpay_desafio_backend.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.prova.picpay_desafio_backend.transaction.Transaction;

/**
 * Serviço de autorização responsável por autorizar transações.
 */
@Service
public class AuthorizerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizerService.class);
  private RestClient restClient;

 
  /**
   * Construtor que inicializa o cliente REST com uma URL base.
   *
   * @param builder o construtor do cliente REST.
   */
  public AuthorizerService(RestClient.Builder builder) {
    this.restClient = builder.baseUrl(
        "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
  }

  
  /**
   * Autoriza uma transação.
   *
   * @param transaction a transação a ser autorizada.
   * @throws UnauthorizedTransactionException se a transação não for autorizada.
   */
  public void authorize(Transaction transaction) {
    LOGGER.info("authorizing transaction {}...", transaction);

    var response = restClient.get().retrieve().toEntity(Authorization.class);
    if (response.getStatusCode().isError() || !response.getBody().isAuthorized())
      throw new UnauthorizedTransactionException("Unauthorized!");
  }
}


/**
 * Um registro que representa uma mensagem de autorização.
 *
 * @param message a mensagem de autorização.
 */
record 	Autorizathion(String message) {
 
	  /**
     * Verifica se a mensagem indica autorização.
     *
     * @return {@code true} se a mensagem for igual a "Autorizado"; {@code false} caso contrário.
     */
	public boolean isAuthorized() {
    return message.equals("Autorizado");
  }
}