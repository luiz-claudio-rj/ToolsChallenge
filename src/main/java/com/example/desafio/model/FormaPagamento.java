package com.example.desafio.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {
    private TipoFormaPagamento tipo;
    private Integer parcelas;
}
