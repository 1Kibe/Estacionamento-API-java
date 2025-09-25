package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.CondicaoPagamento;
import com.ryan.estacionamento_0.repository.CondicaoPagamentoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondicaoPagamentoService {

    @Autowired
    private CondicaoPagamentoRepositoty repository;

    public List<CondicaoPagamento> findAll() {
        return repository.findAll();
    }

    public Optional<CondicaoPagamento> findById(Integer id) {
        return repository.findById(id);
    }

    public CondicaoPagamento save(CondicaoPagamento condicaoPagamento) {
        return repository.save(condicaoPagamento);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}