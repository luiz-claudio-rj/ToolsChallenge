package com.example.desafio.service;

import com.example.desafio.dto.request.TransacaoRequestDTO;
import com.example.desafio.exception.NotFoundException;
import com.example.desafio.exception.UnprocessableException;
import com.example.desafio.model.Descricao;
import com.example.desafio.model.FormaPagamento;
import com.example.desafio.model.Status;
import com.example.desafio.model.Transacao;
import com.example.desafio.repository.TransacaoRepository;
import com.example.desafio.validators.TransacaoRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Classe que contém os serviços relacionados a transação
 */
@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    /**
     * Salvar uma transação
     *
     * @param transacaoRequestDTO transacaoRequestDTO
     * @return Transacao
     */
    public Transacao salvarTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        validarTransacao(transacaoRequestDTO);
        Transacao transacao = criarTransacao(transacaoRequestDTO);
        return transacaoRepository.save(transacao);
    }

    /**
     * Cria uma transação a partir do DTO
     *
     * @param transacaoRequestDTO transacaoRequestDTO
     * @return Transacao
     */
    private static Transacao criarTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        Transacao transacao = new Transacao();

        transacao.setId(transacaoRequestDTO.getTransacao().getId());
        transacao.setCartao(transacaoRequestDTO.getTransacao().getCartao());

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setParcelas(Integer.parseInt(transacaoRequestDTO.getTransacao().getFormaPagamento().getParcelas()));
        formaPagamento.setTipo(transacaoRequestDTO.getTransacao().getFormaPagamento().getTipo());
        transacao.setFormaPagamento(formaPagamento);

        Descricao descricao = new Descricao();
        descricao.setValor(transacaoRequestDTO.getTransacao().getDescricao().getValor());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataHora = LocalDateTime.parse(transacaoRequestDTO.getTransacao().getDescricao().getDataHora(), formatter);
        descricao.setDataHora(dataHora);

        descricao.setEstabelecimento(transacaoRequestDTO.getTransacao().getDescricao().getEstabelecimento());
        descricao.setNsu(UUID.randomUUID().toString().replace("-", ""));
        descricao.setCodigoAutorizacao(UUID.randomUUID().toString().replace("-", ""));
        descricao.setStatus(Status.AUTORIZADO);

        transacao.setDescricao(descricao);
        return transacao;
    }

    /**
     * Valida corpo da requisicao
     *
     * @param transacaoRequestDTO transacaoRequestDTO
     */
    private void validarTransacao(TransacaoRequestDTO transacaoRequestDTO) {
        TransacaoRequestValidator validator = new TransacaoRequestValidator(transacaoRequestDTO, transacaoRepository);

        String validacao = validator.validate();
        if (!validacao.isEmpty()) {
            throw new UnprocessableException(validacao);
        }
    }

    /**
     * Consultar uma transação pelo id
     *
     * @param id id
     * @return Transacao
     */
    public Transacao consultarTransacao(String id) {
        return transacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Transação não encontrada"));
    }

    /**
     * Consultar todas as transações
     * @return List<Transacao>
     */
    public List<Transacao> consultarTodasTransacoes() {
        return transacaoRepository.findAll();
    }

    /**
     * Estornar uma transação
     *
     * @param id id
     * @return Transacao
     */
    public Transacao estornarTransacao(String id) {
        Transacao transacao = transacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Transação não encontrada"));
        transacao.getDescricao().setStatus(Status.NEGADO);
        return transacaoRepository.save(transacao);
    }
}
