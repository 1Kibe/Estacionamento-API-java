package com.ryan.estacionamento_0.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.estacionamento_0.domain.Caixa;
import com.ryan.estacionamento_0.repository.CaixaRepository;

@Service
public class CaixaService {

    @Autowired
    private final CaixaRepository repository;

    public CaixaService(CaixaRepository repository) {
        this.repository = repository;
    }

    public List<Caixa> listarTodos() {
        return repository.findAll();
    }

    public Optional<Caixa> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Caixa salvar(Caixa caixa) {
        return repository.save(caixa);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
