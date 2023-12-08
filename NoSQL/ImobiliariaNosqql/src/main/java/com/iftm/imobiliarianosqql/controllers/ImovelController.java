package com.iftm.imobiliarianosqql.controllers;

import com.iftm.imobiliarianosqql.model.Imovel;
import com.iftm.imobiliarianosqql.services.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService service;

    @GetMapping
    public List<Imovel> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Imovel> findById(@PathVariable String id) {
        Optional<Imovel> imovel = service.findById(id);
        return imovel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Imovel> save(@RequestBody Imovel imovel) {
        Imovel novoImovel = service.save(imovel);
        return ResponseEntity.ok(novoImovel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> update(@PathVariable String id, @RequestBody Imovel novoImovel) {
        Imovel imovelAtualizado = service.update(id, novoImovel);

        if (imovelAtualizado != null) {
            return ResponseEntity.ok(imovelAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarImovel(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
