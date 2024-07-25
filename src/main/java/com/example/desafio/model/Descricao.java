package com.example.desafio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe que representa a descrição da transação
 */
@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Descricao {
  private String valor;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime dataHora;

  private String estabelecimento;
  private String nsu;
  private String codigoAutorizacao;
  private Status status;
}
