package com.ryan.estacionamento_0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.estacionamento_0.domain.Usuario;
import com.ryan.estacionamento_0.repository.query.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer>, UsuarioRepositoryQuery{

}
