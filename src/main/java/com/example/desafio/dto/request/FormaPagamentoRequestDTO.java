package com.example.desafio.dto.request;

import com.example.desafio.model.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoRequestDTO {
    private String tipo;
    private String parcelas;

    public FormaPagamento toEntity() {
        FormaPagamento formaPagamento = new FormaPagamento();

        formaPagamento.setTipo(this.getTipo());
        formaPagamento.setParcelas(Integer.parseInt(this.getParcelas()));

        return formaPagamento;
    }
}
