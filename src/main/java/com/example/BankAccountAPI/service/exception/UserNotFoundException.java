package com.example.BankAccountAPI.service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}
