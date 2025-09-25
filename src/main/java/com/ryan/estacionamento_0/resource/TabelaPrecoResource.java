package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.TabelaPreco;
import com.ryan.estacionamento_0.service.TabelaPrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tabela-preco")
public class TabelaPrecoResource {

    @Autowired
    private TabelaPrecoService service;

    @GetMapping
    public List<TabelaPreco> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaPreco> findById(@PathVariable Integer id) {
        Optional<TabelaPreco> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TabelaPreco create(@RequestBody TabelaPreco obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TabelaPreco> update(@PathVariable Integer id, @RequestBody TabelaPreco obj) {
        Optional<TabelaPreco> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<TabelaPreco> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}