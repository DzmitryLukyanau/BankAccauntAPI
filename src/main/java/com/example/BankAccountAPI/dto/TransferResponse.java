package com.example.BankAccountAPI.dto;

public class TransferResponse {

    private final String message;

    public TransferResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
