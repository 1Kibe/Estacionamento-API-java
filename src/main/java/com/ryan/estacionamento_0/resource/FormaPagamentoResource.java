package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.FormaPagamento;
import com.ryan.estacionamento_0.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService service;

    @GetMapping
    public List<FormaPagamento> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> findById(@PathVariable Integer id) {
        Optional<FormaPagamento> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FormaPagamento create(@RequestBody FormaPagamento obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> update(@PathVariable Integer id, @RequestBody FormaPagamento obj) {
        Optional<FormaPagamento> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<FormaPagamento> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}