package com.example.desafio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {
    private String cartao;
    private String id;
    private DescricaoRequestDTO descricao;
    private FormaPagamentoRequestDTO formaPagamento;

}
