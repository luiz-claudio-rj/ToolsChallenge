package com.example.desafio.service;

import com.example.desafio.model.FormaPagamento;
import com.example.desafio.model.Transacao;
import com.example.desafio.repository.FormaPagamentoRepository;
import com.example.desafio.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public Transacao salvarTransacao(Transacao transacao) {
        FormaPagamento formaPagamento = transacao.getFormaPagamento();

        if(formaPagamento != null && formaPagamento.getId() == null){
            formaPagamento = formaPagamentoRepository.save(formaPagamento);
            transacao.setFormaPagamento(formaPagamento);
        }

        return transacaoRepository.save(transacao);
    }

    public Transacao consultarTransacao(Long id) {
        return transacaoRepository.findById(id).orElse(null);
    }

    public List<Transacao> consultarTodasTransacoes() {
        return transacaoRepository.findAll();
    }
}
