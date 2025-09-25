package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.ControleFluxo;
import com.ryan.estacionamento_0.service.ControleFluxoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controle-fluxo")
public class ControleFluxoResource {

    @Autowired
    private ControleFluxoService service;

    @GetMapping
    public List<ControleFluxo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControleFluxo> findById(@PathVariable Integer id) {
        Optional<ControleFluxo> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ControleFluxo create(@RequestBody ControleFluxo obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ControleFluxo> update(@PathVariable Integer id, @RequestBody ControleFluxo obj) {
        Optional<ControleFluxo> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<ControleFluxo> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}