package com.example.desafio.model;

import com.example.desafio.exception.UnprocessableException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoFormaPagamento {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");


    private final String descricao;

    TipoFormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoFormaPagamento fromString(String value) {
        for (TipoFormaPagamento tipo : TipoFormaPagamento.values()) {
            if (tipo.descricao.equals(value)) {
                return tipo;
            }
        }
        throw new UnprocessableException("Tipo de pagamento inválido: " + value);
    }
}