package com.example.desafio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DescricaoRequestDTO {
    private String valor;
    private String dataHora;
    private String estabelecimento;
}
