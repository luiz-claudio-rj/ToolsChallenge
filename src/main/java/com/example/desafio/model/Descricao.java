package com.example.desafio.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Descricao{
    private String valor;
    private LocalDateTime dataHora;
    private String estabelecimento;
}
