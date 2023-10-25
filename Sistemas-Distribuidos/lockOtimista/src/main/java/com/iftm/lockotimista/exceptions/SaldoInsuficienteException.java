package com.iftm.lockotimista.exceptions;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
