package com.example.BankAccountAPI.service.exception;

public class BalanceException extends RuntimeException {
    public BalanceException() {
        super("Недостаточно средств");
    }
}
