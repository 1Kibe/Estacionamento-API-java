package com.ryan.estacionamento_0.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ryan.estacionamento_0.domain.Cliente;
import com.ryan.estacionamento_0.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {
    Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable);
}
