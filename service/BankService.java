package com.ExpenseTracker.service;

import com.ExpenseTracker.dto.BankDto;
import com.ExpenseTracker.entity.Bank;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public Bank addBank(BankDto dto, User user) {
        Bank bank = Bank.builder()
                .bankName(dto.getBankName())
                .bankType(dto.getBankType())
                .bankStatus(dto.getBankStatus() == null ? "active" : dto.getBankStatus())
                .user(user)
                .build();
        return bankRepository.save(bank);
    }

    public List<Bank> getBanksByUser(User user) {
        return bankRepository.findByUser(user);
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }
}
