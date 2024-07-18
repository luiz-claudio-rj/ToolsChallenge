package com.example.desafio.model;

public enum Status {
    AUTORIZADO("AUTORIZADO"),
    NEGADO("NEGADO");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
