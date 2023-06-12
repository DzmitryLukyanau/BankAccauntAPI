package com.example.BankAccountAPI.service;

import com.example.BankAccountAPI.model.User;
import com.example.BankAccountAPI.repository.UserRepository;
import com.example.BankAccountAPI.service.exception.BalanceException;
import com.example.BankAccountAPI.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getBalance(int getBalance) {
        return userRepository.findById(getBalance)
                .orElseThrow(UserNotFoundException::new);
    }

    public User takeMoney(int id, int balance) {
        User user = getBalance(id);
        if (user.getId() == id) {
            if (user.getBalance() >= balance) {
                balance = user.getBalance() - balance;
                user.setBalance(balance);
            } else {
                throw new BalanceException();
            }
        } else {
            throw new UserNotFoundException();
        }
        return userRepository.save(user);
    }

    public User putMoney(int id, int balance) {
        User user = getBalance(id);
        if (user.getId() == id) {
            balance = user.getBalance() + balance;
            user.setBalance(balance);
        } else {
            throw new UserNotFoundException();
        }
        return userRepository.save(user);
    }
}
