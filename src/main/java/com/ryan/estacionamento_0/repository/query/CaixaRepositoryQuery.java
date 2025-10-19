package com.ryan.estacionamento_0.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ryan.estacionamento_0.domain.Caixa;
import com.ryan.estacionamento_0.repository.filter.CaixaFilter;

public interface CaixaRepositoryQuery {
    Page<Caixa> filtrar(CaixaFilter filter, Pageable pageable);
}
