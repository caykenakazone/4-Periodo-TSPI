package com.iftm.lockotimista.controllers;

import com.iftm.lockotimista.models.ItemPedido;
import com.iftm.lockotimista.models.Produto;
import com.iftm.lockotimista.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item_pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService service;


    @GetMapping
    ResponseEntity<List<ItemPedido>> findAll(){
        return service.findAll();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<ItemPedido> findById(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ItemPedido> create(@RequestBody ItemPedido itemPedido) {
        return service.save(itemPedido);
    }
}
