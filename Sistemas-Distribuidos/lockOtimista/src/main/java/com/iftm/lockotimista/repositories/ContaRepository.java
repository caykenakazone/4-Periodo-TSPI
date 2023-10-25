package com.iftm.lockotimista.repositories;

import com.iftm.lockotimista.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
}
