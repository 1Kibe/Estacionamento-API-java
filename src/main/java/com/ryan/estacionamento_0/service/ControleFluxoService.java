package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.ControleFluxo;
import com.ryan.estacionamento_0.repository.ControleFluxoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControleFluxoService {

    @Autowired
    private ControleFluxoRepository repository;

    public List<ControleFluxo> findAll() {
        return repository.findAll();
    }

    public Optional<ControleFluxo> findById(Integer id) {
        return repository.findById(id);
    }

    public ControleFluxo save(ControleFluxo controleFluxo) {
        return repository.save(controleFluxo);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}