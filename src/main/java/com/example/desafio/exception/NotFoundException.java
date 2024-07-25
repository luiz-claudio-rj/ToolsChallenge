package com.example.desafio.exception;

/**
 * Classe que representa a exceção de recurso não encontrado
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
	super(message);
  }
}
