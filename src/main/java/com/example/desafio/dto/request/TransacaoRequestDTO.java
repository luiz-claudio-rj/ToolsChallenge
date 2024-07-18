package com.example.desafio.dto.request;

import com.example.desafio.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequestDTO {
    private TransacaoDTO transacao;

    public Transacao toEntity() {
        return this.getTransacao().toEntity();
    }
}

