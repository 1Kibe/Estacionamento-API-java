package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.CondItens;
import com.ryan.estacionamento_0.service.CondItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cond-itens")
public class CondItensResource {

    @Autowired
    private CondItensService service;

    @GetMapping
    public List<CondItens> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondItens> findById(@PathVariable Integer id) {
        Optional<CondItens> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CondItens create(@RequestBody CondItens obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondItens> update(@PathVariable Integer id, @RequestBody CondItens obj) {
        Optional<CondItens> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<CondItens> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}