package com.example.desafio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescricaoRequestDTO {
    private String valor;
    private String dataHora;
    private String estabelecimento;
}
