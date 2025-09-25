package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.FormaPagamento;
import com.ryan.estacionamento_0.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> findAll() {
        return repository.findAll();
    }

    public Optional<FormaPagamento> findById(Integer id) {
        return repository.findById(id);
    }

    public FormaPagamento save(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}