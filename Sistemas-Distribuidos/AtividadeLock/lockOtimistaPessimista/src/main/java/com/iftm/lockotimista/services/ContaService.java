package com.iftm.lockotimista.services;

import com.iftm.lockotimista.models.Conta;
import com.iftm.lockotimista.repositories.ContaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void lockPessimista(Integer idDebito, Integer idCredito, double valorTrans){
        Conta contaDebito = entityManager.find(Conta.class, idDebito, LockModeType.PESSIMISTIC_WRITE);
        Conta contaCredito = entityManager.find(Conta.class, idCredito, LockModeType.PESSIMISTIC_WRITE);

        if (contaDebito.getSaldo() >= valorTrans) {
            contaDebito.setSaldo(contaDebito.getSaldo() - valorTrans);
            contaCredito.setSaldo(contaCredito.getSaldo() + valorTrans);
            contaRepository.save(contaDebito);
            contaRepository.save(contaCredito);

            entityManager.persist(contaDebito);
            entityManager.persist(contaCredito);
        }else{
            System.out.println("Não há saldo suficiente na conta!");
        }

    }
    @Transactional
    public void lockOtimista(Integer idDebito, Integer idCredito, double valorTrans) {
        Conta contaDebito = contaRepository.getOne(idDebito);
        Conta contaCredito = contaRepository.getOne(idCredito);

        if (contaDebito.getSaldo() >= valorTrans) {
            contaDebito.setSaldo(contaDebito.getSaldo() - valorTrans);
            contaCredito.setSaldo(contaCredito.getSaldo() + valorTrans);
            contaRepository.save(contaDebito);
            contaRepository.save(contaCredito);
        }else{
            System.out.println("Não há saldo suficiente na conta!");
        }
    }

    public List<Conta> findAll() {
        return contaRepository.findAll();
    }
}
