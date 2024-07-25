package com.example.desafio.controller;

import com.example.desafio.dto.request.TransacaoRequestDTO;
import com.example.desafio.model.Transacao;
import com.example.desafio.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe que representa o controller da transação
 */
@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    /**
     * Realiza uma transação
     *
     * @param transacaoRequestDTO transacaoRequestDTO
     * @return ResponseEntity<Transacao>
     */
    @PostMapping("/pagamento")
    public ResponseEntity<Transacao> realizarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        Transacao transacao = transacaoService.salvarTransacao(transacaoRequestDTO);
        return ResponseEntity.ok(transacao);
    }

    /**
     * Estorna uma transação
     *
     * @param id String
     * @return
     * ResponseEntity<Transacao>
     */
    @GetMapping("/estorno/{id}")
    public ResponseEntity<Transacao> estornarPagamento(@PathVariable String id) {
        Transacao transacao = transacaoService.estornarTransacao(id);
        return transacao != null ? ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }

    /**
     * Consulta uma transação
     *
     * @param id String
     * @return ResponseEntity<Transacao>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transacao> consultarTransacao(@PathVariable String id) {
        Transacao transacao = transacaoService.consultarTransacao(id);
        return transacao != null ? ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }

    /**
     * Consulta todas as transações
     *
     * @return ResponseEntity<List<Transacao>>
     */
    @GetMapping
    public ResponseEntity<List<Transacao>> consultarTodasTransacoes() {
        List<Transacao> transacoes = transacaoService.consultarTodasTransacoes();
        return ResponseEntity.ok(transacoes);
    }
}