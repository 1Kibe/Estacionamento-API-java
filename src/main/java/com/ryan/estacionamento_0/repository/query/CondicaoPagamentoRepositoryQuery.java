package com.ryan.estacionamento_0.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ryan.estacionamento_0.domain.CondicaoPagamento;
import com.ryan.estacionamento_0.repository.filter.CondicaoPagamentoFilter;

public interface CondicaoPagamentoRepositoryQuery {
    Page<CondicaoPagamento> filtrar(CondicaoPagamentoFilter filter, Pageable pageable);
}
