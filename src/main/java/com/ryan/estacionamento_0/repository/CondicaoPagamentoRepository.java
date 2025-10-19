package com.ryan.estacionamento_0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.estacionamento_0.domain.CondicaoPagamento;
import com.ryan.estacionamento_0.repository.query.CondicaoPagamentoRepositoryQuery;

public interface CondicaoPagamentoRepository extends JpaRepository<CondicaoPagamento, Integer>, CondicaoPagamentoRepositoryQuery {

}
