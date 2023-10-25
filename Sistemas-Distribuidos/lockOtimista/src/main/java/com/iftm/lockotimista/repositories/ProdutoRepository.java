package com.iftm.lockotimista.repositories;

import com.iftm.lockotimista.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
}
