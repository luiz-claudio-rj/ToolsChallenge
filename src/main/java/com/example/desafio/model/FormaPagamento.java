package com.example.desafio.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa a Forma de Pagamento da transação
 */
@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
  private TipoFormaPagamento tipo;
  private Integer parcelas;
}
