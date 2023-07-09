package com.example.BankAccountAPI.dto;

import java.time.LocalDate;

public class OperationRequest {
    LocalDate start;
    LocalDate end;

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
