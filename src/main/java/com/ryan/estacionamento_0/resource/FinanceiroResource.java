package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.Financeiro;
import com.ryan.estacionamento_0.service.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroResource {

    @Autowired
    private FinanceiroService service;

    @GetMapping
    public List<Financeiro> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financeiro> findById(@PathVariable Integer id) {
        Optional<Financeiro> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Financeiro create(@RequestBody Financeiro obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Financeiro> update(@PathVariable Integer id, @RequestBody Financeiro obj) {
        Optional<Financeiro> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Financeiro> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}