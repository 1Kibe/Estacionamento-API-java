package com.ryan.estacionamento_0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.estacionamento_0.domain.Cliente;
import com.ryan.estacionamento_0.repository.query.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>, ClienteRepositoryQuery {

}
