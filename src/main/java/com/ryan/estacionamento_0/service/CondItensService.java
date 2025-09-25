package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.CondItens;
import com.ryan.estacionamento_0.repository.CondItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondItensService {

    @Autowired
    private CondItensRepository repository;

    public List<CondItens> findAll() {
        return repository.findAll();
    }

    public Optional<CondItens> findById(Integer id) {
        return repository.findById(id);
    }

    public CondItens save(CondItens condItens) {
        return repository.save(condItens);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}