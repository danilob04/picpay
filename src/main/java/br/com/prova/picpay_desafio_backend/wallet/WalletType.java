package br.com.prova.picpay_desafio_backend.wallet;

/**
 * Enumeração que define os tipos de carteira.
 */
public enum WalletType {
    COMUM(1), LOJISTA(2);
  
    private int value;
  
    /**
     * Construtor privado que associa um valor inteiro a cada tipo de carteira.
     *
     * @param value o valor inteiro associado ao tipo de carteira.
     */
    private WalletType(int value) {
      this.value = value;
    }
  
    
    /**
     * Obtém o valor inteiro associado ao tipo de carteira.
     *
     * @return o valor inteiro associado ao tipo de carteira.
     */
    public int getValue() {
      return value;
    }
  }