package com.ExpenseTracker.controller;

import com.ExpenseTracker.dto.BankAccountDto;
import com.ExpenseTracker.entity.BankAccount;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.service.BankAccountService;
import com.ExpenseTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final UserService userService;

    @PostMapping
    public BankAccount addBankAccount(@RequestBody BankAccountDto dto,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return bankAccountService.addBankAccount(dto, user);
    }

    @GetMapping
    public List<BankAccount> getUserBankAccounts(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return bankAccountService.getUserBankAccounts(user);
    }

    @DeleteMapping("/{id}")
    public String deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return "Bank account deleted successfully.";
    }
}
