package com.ryan.estacionamento_0.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.estacionamento_0.domain.Caixa;
import com.ryan.estacionamento_0.service.CaixaService;

@RestController
@RequestMapping("/caixa")
public class CaixaResource {

    @Autowired
    private final CaixaService service;

    public CaixaResource(CaixaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Caixa>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Caixa> salvar(@RequestBody Caixa caixa) {
        Caixa novoCaixa = service.salvar(caixa);
        return ResponseEntity.ok(novoCaixa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa> atualizar(@PathVariable Integer id, @RequestBody Caixa caixa) {
        return service.buscarPorId(id)
                .map(existente -> {
                    caixa.setId(id);
                    Caixa atualizado = service.salvar(caixa);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
