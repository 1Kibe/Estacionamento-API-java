package com.ryan.estacionamento_0.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ryan.estacionamento_0.domain.Caixa;
import com.ryan.estacionamento_0.repository.query.CaixaRepositoryQuery;

public interface CaixaRepository extends JpaRepository <Caixa,Integer>, CaixaRepositoryQuery{

    @Query(value = "select * from caixa where id = ?",nativeQuery = true)
    Caixa buscarPorId(int id);

    @Query(value = "select * from caixa", nativeQuery = true)
    List<Caixa> listarTodos();
}
