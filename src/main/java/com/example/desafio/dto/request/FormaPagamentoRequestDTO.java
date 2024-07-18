package com.example.desafio.dto.request;

import com.example.desafio.model.TipoFormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoRequestDTO {
    private TipoFormaPagamento tipo;
    private String parcelas;
}
