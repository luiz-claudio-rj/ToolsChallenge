package com.example.desafio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {
    private String cartao;
    private String id;
    private DescricaoRequestDTO descricao;
    private FormaPagamentoRequestDTO formaPagamento;

}
