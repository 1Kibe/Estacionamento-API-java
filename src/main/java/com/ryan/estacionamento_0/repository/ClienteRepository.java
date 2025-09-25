package com.ryan.estacionamento_0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.estacionamento_0.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
