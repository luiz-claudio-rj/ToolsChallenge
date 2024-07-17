package com.example.desafio.controller;
import com.example.desafio.model.Transacao;
import com.example.desafio.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/pagamento")
    public ResponseEntity<Transacao> realizarPagamento(@RequestBody Transacao transacao) {
        try {
            Transacao novaTransacao = transacaoService.salvarTransacao(transacao);
            return ResponseEntity.ok(novaTransacao);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/estorno/{id}")
    public ResponseEntity<Transacao> estornarPagamento(@PathVariable Long id) {
            Transacao transacao = transacaoService.estornarTransacao(id);
            return transacao != null ? ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> consultarTransacao(@PathVariable Long id) {
        Transacao transacao = transacaoService.consultarTransacao(id);
        return transacao != null ?  ResponseEntity.ok(transacao) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> consultarTodasTransacoes() {
        List<Transacao> transacoes = transacaoService.consultarTodasTransacoes();
        return ResponseEntity.ok(transacoes);
    }
}