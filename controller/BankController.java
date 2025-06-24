package com.ExpenseTracker.controller;

import com.ExpenseTracker.dto.BankDto;
import com.ExpenseTracker.entity.Bank;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.service.BankService;
import com.ExpenseTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final UserService userService;

    @PostMapping
    public Bank createBank(@RequestBody BankDto dto,
                           @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return bankService.addBank(dto, user);
    }

    @GetMapping
    public List<Bank> getUserBanks(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return bankService.getBanksByUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return "Bank deleted successfully.";
    }
}
