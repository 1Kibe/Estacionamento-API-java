package com.ryan.estacionamento_0.resource;

import com.ryan.estacionamento_0.domain.CondicaoPagamento;
import com.ryan.estacionamento_0.repository.filter.CondicaoPagamentoFilter;
import com.ryan.estacionamento_0.service.CondicaoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/condicao-pagamento")
public class CondicaoPagamentoResource {

    @Autowired
    private CondicaoPagamentoService service;

    @GetMapping
    public List<CondicaoPagamento> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicaoPagamento> findById(@PathVariable Integer id) {
        Optional<CondicaoPagamento> obj = service.findById(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filtro")
    public Page<CondicaoPagamento> filtro(CondicaoPagamentoFilter filter, Pageable pageable) {
        return service.filtrar(filter, pageable);
    }
    

    @PostMapping
    public CondicaoPagamento create(@RequestBody CondicaoPagamento obj) {
        return service.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondicaoPagamento> update(@PathVariable Integer id, @RequestBody CondicaoPagamento obj) {
        Optional<CondicaoPagamento> existing = service.findById(id);
        if (existing.isPresent()) {
            obj.setId(id);
            return ResponseEntity.ok(service.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<CondicaoPagamento> existing = service.findById(id);
        if (existing.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}