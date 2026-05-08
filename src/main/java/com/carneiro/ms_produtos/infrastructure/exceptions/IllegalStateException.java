package com.carneiro.ms_produtos.infrastructure.exceptions;

public class IllegalStateException extends RuntimeException {
    public IllegalStateException(String message) {
        super(message);
    }
}
