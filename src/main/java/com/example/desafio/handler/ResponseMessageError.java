package com.example.desafio.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Classe que representa a mensagem de erro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageError {
    private LocalDateTime dateTime;
    private String message;
    private String path;
}