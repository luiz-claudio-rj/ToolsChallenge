package com.example.desafio.validators;

import com.example.desafio.dto.request.DescricaoRequestDTO;
import com.example.desafio.dto.request.FormaPagamentoRequestDTO;
import com.example.desafio.dto.request.TransacaoDTO;
import com.example.desafio.dto.request.TransacaoRequestDTO;
import com.example.desafio.model.Descricao;
import com.example.desafio.model.FormaPagamento;
import com.example.desafio.model.TipoFormaPagamento;
import com.example.desafio.model.Transacao;
import com.example.desafio.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoRequestValidatorTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private TransacaoRepository transacaoRepository;

    @Test
    void quandoDadosValidos_entaoValidacaoSucesso() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO();

        TransacaoDTO transacao = new TransacaoDTO();
        transacao.setId("123");
        transacao.setCartao("1234");

        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        descricao.setValor("100.00");
        descricao.setDataHora("01/05/2021 18:30:00");
        descricao.setEstabelecimento("Estabelecimento");
        transacao.setDescricao(descricao);

        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        formaPagamento.setParcelas("1");
        transacao.setFormaPagamento(formaPagamento);

        dto.setTransacao(transacao);

        TransacaoRequestValidator validator = new TransacaoRequestValidator();
        validator.setTransacaoRepository(transacaoRepository);
        validator.setTransacao(dto);

        String validacao = validator.validate();

        assertTrue(validacao.isEmpty());
    }

    @Test
    void quandoDadosInvalidos_entaoValidacaoErro() {
        TransacaoRequestValidator validator = new TransacaoRequestValidator();
        validator.setTransacaoRepository(transacaoRepository);

        TransacaoRequestDTO dto = new TransacaoRequestDTO();
        validator.setTransacao(dto);
        assertEquals("Transacao não pode ser nula",validator.validate());

        TransacaoDTO transacao = new TransacaoDTO();
        dto.setTransacao(transacao);
        assertEquals("Verificar id da transação",validator.validate());

        transacao.setId("123");
        dto.setTransacao(transacao);
        assertEquals("Verificar cartão da transação",validator.validate());

        transacao.setCartao("1234");
        dto.setTransacao(transacao);
        assertEquals("Verificar descrição da transação",validator.validate());

        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar valor da transação",validator.validate());

        descricao.setValor("100.00");

        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar dataHora da transação",validator.validate());

        descricao.setDataHora("01/05/2021 18:30:00");
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar establecimento da transação",validator.validate());

        descricao.setEstabelecimento("Estabelecimento");
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar forma de pagamento da transação",validator.validate());

        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar parcelas da transação",validator.validate());

        formaPagamento.setParcelas("1");
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar tipo da transação",validator.validate());

        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);

        assertTrue(validator.validate().isEmpty());
    }

}