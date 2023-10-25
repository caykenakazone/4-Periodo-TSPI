package com.iftm.lockotimista.repositories;

import com.iftm.lockotimista.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer> {
}
