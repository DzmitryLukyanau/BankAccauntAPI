package com.example.BankAccountAPI.controller.handler;

import com.example.BankAccountAPI.dto.ErrorResponse;
import com.example.BankAccountAPI.service.exception.BalanceException;
import com.example.BankAccountAPI.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundPlanetException(
            UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<ErrorResponse> handlerBalanceException(
            BalanceException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getMessage()));
    }
}