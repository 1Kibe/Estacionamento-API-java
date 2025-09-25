package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.Vagas;
import com.ryan.estacionamento_0.repository.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagasService {

    @Autowired
    private VagasRepository repository;

    public List<Vagas> findAll() {
        return repository.findAll();
    }

    public Optional<Vagas> findById(Integer id) {
        return repository.findById(id);
    }

    public Vagas save(Vagas vagas) {
        return repository.save(vagas);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}