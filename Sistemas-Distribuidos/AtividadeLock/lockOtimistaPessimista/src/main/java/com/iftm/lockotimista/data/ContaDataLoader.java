package com.iftm.lockotimista.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.iftm.lockotimista.models.Conta;
import com.iftm.lockotimista.repositories.ContaRepository;

@Component
public class ContaDataLoader implements CommandLineRunner {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaDataLoader(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Conta conta1 = new Conta(null, "12345", 1000.0);
        Conta conta2 = new Conta(null, "67890", 1500.0);
        Conta conta3 = new Conta(null, "54321", 2000.0);
        Conta conta4 = new Conta(null, "98765", 2500.0);

        contaRepository.save(conta1);
        contaRepository.save(conta2);
        contaRepository.save(conta3);
        contaRepository.save(conta4);

        System.out.println("Contas de teste criadas e salvas no banco de dados!");
    }
}
