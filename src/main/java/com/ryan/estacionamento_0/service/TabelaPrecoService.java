package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.TabelaPreco;
import com.ryan.estacionamento_0.repository.TabelaPrecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabelaPrecoService {

    @Autowired
    private TabelaPrecoRepository repository;

    public List<TabelaPreco> findAll() {
        return repository.findAll();
    }

    public Optional<TabelaPreco> findById(Integer id) {
        return repository.findById(id);
    }

    public TabelaPreco save(TabelaPreco tabelaPreco) {
        return repository.save(tabelaPreco);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}