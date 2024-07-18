package com.example.desafio.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.desafio.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TransacaoServiceTest {

    @Autowired
    private TransacaoService transacaoService;

    @MockBean
    private TransacaoRepository transacaoRepository;

    // Implementar testes para salvarTransacao, consultarTransacao, etc.
}