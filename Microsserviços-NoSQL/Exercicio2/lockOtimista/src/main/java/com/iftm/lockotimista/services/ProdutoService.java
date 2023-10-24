package com.iftm.lockotimista.services;

import com.iftm.lockotimista.models.Produto;
import com.iftm.lockotimista.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public ResponseEntity<List<Produto>> findAll(){
        var dbProduct = repository.findAll();
        if (dbProduct.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dbProduct);
    }
    public ResponseEntity<Produto> findById(Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        var produto = repository.findById(id);
        if (produto.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new Produto(produto.get()));
    }
    public ResponseEntity<Produto> save(Produto produto) {
        var savedProdutos = repository.save(produto);;
        return ResponseEntity.ok(new Produto(savedProdutos));
    }
}
