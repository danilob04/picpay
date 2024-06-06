package br.com.prova.picpay_desafio_backend.authorization;

/**
 * Um registro que representa uma mensagem de autorização.
 *
 * @param message a mensagem de autorização.
 */
public record Authorization(
    String message) {
   
	
/**
     * Verifica se a mensagem indica autorização.
     *
     * @return {@code true} se a mensagem for igual a "Autorizado"; {@code false} caso contrário.
     */
	public boolean isAuthorized(){
        return message.equals("Autorizado");
    }
}
