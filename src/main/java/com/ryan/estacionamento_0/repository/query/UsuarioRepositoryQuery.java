package com.ryan.estacionamento_0.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ryan.estacionamento_0.domain.Usuario;
import com.ryan.estacionamento_0.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {

    // Método para filtrar usuários com paginação
    // Implementação será feita na classe que implementa esta interface
    Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable);
}
