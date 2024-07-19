package com.example.desafio.validators;

import com.example.desafio.dto.request.TransacaoRequestDTO;
import com.example.desafio.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequestValidator {

    private TransacaoRequestDTO transacao;
    private TransacaoRepository transacaoRepository;

    public TransacaoRequestValidator(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public String validate() {
        if (transacao == null) {
            return "Transação não pode ser nula";
        }
        if (transacao.getTransacao() == null) {
            return "Transação não pode ser nula";
        }
        if (transacao.getTransacao().getId() == null || transacao.getTransacao().getId().isEmpty()) {
            return "Verificar id da transação";
        }
        if (transacao.getTransacao().getCartao() == null || transacao.getTransacao().getCartao().isEmpty()) {
            return "Verificar cartão da transação";
        }
        if (transacao.getTransacao().getDescricao() == null) {
            return "Verificar descrição da transação";
        }
        if (transacao.getTransacao().getDescricao().getValor() == null || transacao.getTransacao().getDescricao().getValor().isEmpty()) {
            return "Verificar valor da transação";
        }
        if (transacao.getTransacao().getDescricao().getDataHora() == null || transacao.getTransacao().getDescricao().getDataHora().isEmpty()) {
            return "Verificar data e hora da transação";
        }
        if (transacao.getTransacao().getDescricao().getEstabelecimento() == null || transacao.getTransacao().getDescricao().getEstabelecimento().isEmpty()) {
            return "Verificar estabelecimento da transação";
        }
        if (transacao.getTransacao().getFormaPagamento() == null) {
            return "Verificar forma de pagamento da transação";
        }
        if (transacao.getTransacao().getFormaPagamento().getParcelas() == null || transacao.getTransacao().getFormaPagamento().getParcelas().isEmpty()) {
            return "Verificar parcelas da transação";
        }
        if (transacao.getTransacao().getFormaPagamento().getTipo() == null) {
            return "Verificar tipo da forma de pagamento";
        }

        try {
            Long.valueOf(transacao.getTransacao().getId());
        } catch (NumberFormatException e) {
            return "Id inválido";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime.parse(transacao.getTransacao().getDescricao().getDataHora(), formatter);
        } catch (Exception e) {
            return "Data e hora inválidas";
        }

        try {
            String valorString = transacao.getTransacao().getDescricao().getValor();
            Pattern pattern = Pattern.compile("^\\d+\\.\\d{2}$");
            if (!pattern.matcher(valorString).matches()) {
                return "Valor inválido";
            }
            double valorNumerico = Double.parseDouble(transacao.getTransacao().getDescricao().getValor());
            if (valorNumerico <= 0) {
                return "Valor inválido";
            }
        } catch (NumberFormatException e) {
            return "Valor inválido";
        }

        try {
            Integer parcela = Integer.parseInt(transacao.getTransacao().getFormaPagamento().getParcelas());
            if (parcela <= 0) {
                return "Parcelas inválidas";
            }
        } catch (NumberFormatException e) {
            return "Parcelas inválidas";
        }

        if (transacaoRepository.existsById(Long.valueOf(transacao.getTransacao().getId()))) {
            return "Transação já existente";
        }

        return "";
    }
}
