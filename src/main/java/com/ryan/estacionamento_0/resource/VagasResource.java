package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.Vagas;
import com.ryan.estacionamento_0.service.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
public class VagasResource {

    @Autowired
    private VagasService service;

    @GetMapping
    public List<Vagas> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vagas> findById(@PathVariable Integer id) {
        Optional<Vagas> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vagas create(@RequestBody Vagas obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vagas> update(@PathVariable Integer id, @RequestBody Vagas obj) {
        Optional<Vagas> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Vagas> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}