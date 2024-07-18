package com.example.desafio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponseDTO {
    private TransacaoDTO transacao;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TransacaoDTO {
    private String cartao;
    private String id;
    private DescricaoResponseDTO descricao;
    private FormaPagamentoResponseDTO formaPagamento;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DescricaoResponseDTO {
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private String status;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FormaPagamentoResponseDTO {
    private String tipo;
    private String parcelas;
}