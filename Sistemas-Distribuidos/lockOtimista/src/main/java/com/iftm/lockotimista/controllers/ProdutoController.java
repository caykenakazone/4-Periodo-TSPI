package com.iftm.lockotimista.controllers;

import com.iftm.lockotimista.models.ItemPedido;
import com.iftm.lockotimista.models.Produto;
import com.iftm.lockotimista.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
public class ProdutoController{

    @Autowired
    private ProdutoService service;


    @GetMapping
    ResponseEntity<List<Produto>> findAll(){
        return service.findAll();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Produto> findById(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        return service.save(produto);
    }
}
