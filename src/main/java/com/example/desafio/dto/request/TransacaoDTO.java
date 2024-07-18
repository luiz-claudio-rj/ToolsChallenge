package com.example.desafio.dto.request;

import com.example.desafio.model.Transacao;
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

    public Transacao toEntity() {
        Transacao transacao = new Transacao();

        transacao.setCartao(this.getCartao());
        transacao.setId(this.getId());
        transacao.setDescricao(this.getDescricao().toEntity());

        transacao.setFormaPagamento(this.getFormaPagamento().toEntity());

        return transacao;
    }
}
