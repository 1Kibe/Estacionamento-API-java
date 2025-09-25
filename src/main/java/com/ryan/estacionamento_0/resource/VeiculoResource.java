package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.Veiculo;
import com.ryan.estacionamento_0.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
public class VeiculoResource {

    @Autowired
    private VeiculoService service;

    @GetMapping
    public List<Veiculo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Integer id) {
        Optional<Veiculo> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Veiculo create(@RequestBody Veiculo obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable Integer id, @RequestBody Veiculo obj) {
        Optional<Veiculo> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Veiculo> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}