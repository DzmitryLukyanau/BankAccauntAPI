package com.example.BankAccountAPI.controller;

import com.example.BankAccountAPI.dto.*;
import com.example.BankAccountAPI.dto.PutMoneyRequest;
import com.example.BankAccountAPI.dto.TakeMoneyRequest;
import com.example.BankAccountAPI.dto.UserResponse;
import com.example.BankAccountAPI.model.User;
import com.example.BankAccountAPI.service.OperationService;
import com.example.BankAccountAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final OperationService operationService;

    @Autowired
    public UserController(UserService userService, OperationService operationService) {
        this.userService = userService;
        this.operationService = operationService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getBalance(@PathVariable("id") int id) {
        User user = userService.getBalance(id);
        return new UserResponse(user.getId(), user.getBalance());
    }

    @PatchMapping(value = "/takeMoney/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse takeMoney(@PathVariable("id") int id,
                                  @RequestBody TakeMoneyRequest request) {
        User user = userService.takeMoney(id, request.getTakeMoney());
        return new UserResponse(user.getId(), user.getBalance());
    }

    @PatchMapping(value = "/putMoney/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse putMoney(@PathVariable("id") int id,
                                 @RequestBody PutMoneyRequest request) {
        User user = userService.putMoney(id, request.getPutMoney());
        return new UserResponse(user.getId(), user.getBalance());
    }

    @GetMapping("{id}/operations")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResponse> getOperationList(@PathVariable("id") int id,
                                                    @RequestParam (value = "start", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                    @RequestParam (value = "end", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end) {
        return operationService.getOperationList(id, start, end).stream()
                .map(operation -> new OperationResponse(operation.getId(), operation.getUserId(),
                        operation.getDate(), operation.getSum(), operation.getTypeOfOperation()))
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/transferMoney")
    @ResponseStatus(HttpStatus.OK)
    public TransferResponse transferMoney(@RequestBody TransferRequest request) {
        userService.transferMoney(request.getIdFromWhom(), request.getIdFromTo(), request.getBalance());
        return new TransferResponse("Операция прошла успешно");
    }

}
