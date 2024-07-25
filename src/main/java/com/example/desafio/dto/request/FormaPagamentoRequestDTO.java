package com.example.desafio.dto.request;

import com.example.desafio.model.TipoFormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoRequestDTO {
    private TipoFormaPagamento tipo;
    private String parcelas;
}
