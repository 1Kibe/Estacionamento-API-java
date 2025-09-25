package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.Financeiro;
import com.ryan.estacionamento_0.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroService {

    @Autowired
    private FinanceiroRepository repository;

    public List<Financeiro> findAll() {
        return repository.findAll();
    }

    public Optional<Financeiro> findById(Integer id) {
        return repository.findById(id);
    }

    public Financeiro save(Financeiro financeiro) {
        return repository.save(financeiro);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}