package com.example.desafio.service;

import com.example.desafio.dto.request.DescricaoRequestDTO;
import com.example.desafio.dto.request.FormaPagamentoRequestDTO;
import com.example.desafio.dto.request.TransacaoDTO;
import com.example.desafio.dto.request.TransacaoRequestDTO;
import com.example.desafio.exception.NotFoundException;
import com.example.desafio.exception.UnprocessableException;
import com.example.desafio.model.Descricao;
import com.example.desafio.model.Status;
import com.example.desafio.model.TipoFormaPagamento;
import com.example.desafio.model.Transacao;
import com.example.desafio.repository.TransacaoRepository;
import com.example.desafio.validators.TransacaoRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private TransacaoRequestValidator validator;

    @InjectMocks
    private TransacaoService transacaoService;

    private TransacaoRequestDTO transacaoRequestDTO;

    @BeforeEach
    void setUp() {
        transacaoRequestDTO = new TransacaoRequestDTO();

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

        transacaoRequestDTO.setTransacao(transacao);
    }

    @Test
    void salvarTransacao_sucesso() {
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(new Transacao());

        Transacao result = transacaoService.salvarTransacao(transacaoRequestDTO);

        assertNotNull(result);
        verify(transacaoRepository, times(1)).save(any(Transacao.class));
    }

    @Test
    void salvarTransacao_erroUnprocessableException() {
        TransacaoRequestDTO transacao = new TransacaoRequestDTO();

        UnprocessableException exception = assertThrows(UnprocessableException.class, () -> {
            transacaoService.salvarTransacao(transacao);
        });

        assertEquals("Transacao não pode ser nula", exception.getMessage());
    }

    @Test
    void consultarTransacao_sucesso() {
        Transacao transacao = new Transacao();
        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.of(transacao));

        Transacao result = transacaoService.consultarTransacao(1L);

        assertNotNull(result);
        verify(transacaoRepository, times(1)).findById(1L);
    }

    @Test
    void consultarTransacao_naoEncontrada() {
        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            transacaoService.consultarTransacao(1L);
        });

        assertEquals("Transação não encontrada", thrown.getMessage());
    }

    @Test
    void consultarTodasTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        when(transacaoRepository.findAll()).thenReturn(transacoes);

        List<Transacao> result = transacaoService.consultarTodasTransacoes();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(transacaoRepository, times(1)).findAll();
    }

    @Test
    void estornarTransacao_sucesso() {
        Transacao transacao = new Transacao();
        Descricao descricao = new Descricao();
        transacao.setDescricao(descricao);
        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.of(transacao));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao result = transacaoService.estornarTransacao(1L);

        assertNotNull(result);
        assertEquals(Status.NEGADO, result.getDescricao().getStatus());
        verify(transacaoRepository, times(1)).findById(1L);
        verify(transacaoRepository, times(1)).save(any(Transacao.class));
    }

    @Test
    void estornarTransacao_naoEncontrada() {
        when(transacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            transacaoService.estornarTransacao(1L);
        });

        assertEquals("Transação não encontrada", thrown.getMessage());
    }
}
