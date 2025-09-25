package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.Veiculo;
import com.ryan.estacionamento_0.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    public Optional<Veiculo> findById(Integer id) {
        return repository.findById(id);
    }

    public Veiculo save(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}