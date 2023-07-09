package com.example.BankAccountAPI.service;

import com.example.BankAccountAPI.model.Operation;
import com.example.BankAccountAPI.repository.OperationsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperationService {
    private final OperationsRepository operationsRepository;

    public OperationService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public List<Operation> getOperationList(int id, LocalDate start, LocalDate end) {
        return operationsRepository.getOperations(id, start.toString(), end.toString());
    }
}
