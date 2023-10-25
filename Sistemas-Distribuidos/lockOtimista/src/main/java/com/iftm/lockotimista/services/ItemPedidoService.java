package com.iftm.lockotimista.services;

import com.iftm.lockotimista.models.ItemPedido;
import com.iftm.lockotimista.models.Produto;
import com.iftm.lockotimista.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public ResponseEntity<List<ItemPedido>> findAll() {
        var dbItem = repository.findAll();
        if (dbItem.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dbItem);
    }

    public ResponseEntity<ItemPedido> findById(Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        var itemPedido = repository.findById(id);
        if (itemPedido.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ItemPedido(itemPedido.get()));
    }

    public ResponseEntity<ItemPedido> save(ItemPedido itemPedido) {
        var savedItens = repository.save(itemPedido);
        return ResponseEntity.ok(new ItemPedido(savedItens));

    }
}
