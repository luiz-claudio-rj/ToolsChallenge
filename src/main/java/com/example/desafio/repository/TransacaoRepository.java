package com.example.desafio.repository;

import com.example.desafio.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório da transação
 */
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {
}