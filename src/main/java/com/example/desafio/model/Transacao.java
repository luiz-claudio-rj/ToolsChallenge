package com.example.desafio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cartao;
    private String status;
    private String nsu;
    private String codigoAutorizacao;

    @Embedded
    private Descricao descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    private FormaPagamento formaPagamento;
}


