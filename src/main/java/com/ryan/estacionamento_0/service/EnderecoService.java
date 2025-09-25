package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.Endereco;
import com.ryan.estacionamento_0.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Optional<Endereco> findById(Integer id) {
        return repository.findById(id);
    }

    public Endereco save(Endereco endereco) {
        return repository.save(endereco);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}