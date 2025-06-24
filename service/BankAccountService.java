package com.ExpenseTracker.service;

import com.ExpenseTracker.dto.BankAccountDto;
import com.ExpenseTracker.entity.BankAccount;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccount addBankAccount(BankAccountDto dto, User user) {
        BankAccount account = BankAccount.builder()
                .bankName(dto.getBankName())
                .branchName(dto.getBranchName())
                .accountNumber(dto.getAccountNumber())
                .ifscCode(dto.getIfscCode())
                .accountType(dto.getAccountType())
                .user(user)
                .build();
        return bankAccountRepository.save(account);
    }

    public List<BankAccount> getUserBankAccounts(User user) {
        return bankAccountRepository.findByUser(user);
    }

    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
