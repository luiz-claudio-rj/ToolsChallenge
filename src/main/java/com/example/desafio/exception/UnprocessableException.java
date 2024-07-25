package com.example.desafio.exception;

/**
 * Classe que representa a exceção de recurso não encontrado
 */
public class UnprocessableException extends RuntimeException {
    public UnprocessableException(String message) {
        super(message);
    }
}
