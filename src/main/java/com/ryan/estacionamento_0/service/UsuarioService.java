package com.ryan.estacionamento_0.service;

import com.ryan.estacionamento_0.domain.Usuario;
import com.ryan.estacionamento_0.repository.UsuarioRepository;
import com.ryan.estacionamento_0.repository.filter.UsuarioFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return repository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
        return repository.filtrar(filter, pageable);
    }
}