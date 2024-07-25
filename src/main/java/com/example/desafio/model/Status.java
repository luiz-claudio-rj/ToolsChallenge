package com.example.desafio.model;

import lombok.Getter;

/**
 * Enum que representa o status da transação
 */
@Getter
public enum Status {
    AUTORIZADO("AUTORIZADO"),
    NEGADO("NEGADO");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

}
