package com.example.desafio.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa a transação
 */
@Entity
@Setter
@Getter
public class Transacao {
  @Id
  private String id;
  private String cartao;

  @Embedded
  private Descricao descricao;

  @Embedded
  private FormaPagamento formaPagamento;
}


