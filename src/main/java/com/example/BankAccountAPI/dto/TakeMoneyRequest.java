package com.example.BankAccountAPI.dto;

import javax.validation.constraints.PositiveOrZero;

public class TakeMoneyRequest {

    @PositiveOrZero(message = "Недостаточно средств")
    int takeMoney;

    public int getTakeMoney() {
        return takeMoney;
    }

}
