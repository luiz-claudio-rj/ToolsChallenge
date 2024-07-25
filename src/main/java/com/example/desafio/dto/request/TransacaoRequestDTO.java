package com.example.desafio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Classe que representa a requisição de uma transação
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequestDTO {

    private TransacaoDTO transacao;
}

