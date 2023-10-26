package com.iftm.lockotimista.controllers;

import com.iftm.lockotimista.models.Conta;
import com.iftm.lockotimista.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/otimista")
    public void lockOtimista(@RequestBody Map<String, Object> requestBody) {
        Integer idDebito = (Integer) requestBody.get("idDebito");
        Integer idCredito = (Integer) requestBody.get("idCredito");
        Double valorTrans = (Double) requestBody.get("valorTrans");

        contaService.lockOtimista(idDebito, idCredito, valorTrans);
    }
    @PostMapping("/pessimista")
    public void lockPessimista(@RequestBody Map<String, Object> requestBody) {
        Integer idDebito = (Integer) requestBody.get("idDebito");
        Integer idCredito = (Integer) requestBody.get("idCredito");
        Double valorTrans = (Double) requestBody.get("valorTrans");

        contaService.lockPessimista(idDebito, idCredito, valorTrans);
    }
    @GetMapping
    public List<Conta> findAll() {
        return contaService.findAll();
    }
}
