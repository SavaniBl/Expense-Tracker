package com.ExpenseTracker.controller;

import com.ExpenseTracker.Enum.LoanStatus;
import com.ExpenseTracker.dto.LoanStatusUpdateRequest;
import com.ExpenseTracker.entity.Loan;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.service.LoanService;
import com.ExpenseTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final UserService userService;

    @PostMapping
    public Loan addLoan(@RequestBody Loan loan, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        loan.setUser(user);
        loan.setStatus(LoanStatus.PENDING); // default status
        return loanService.addLoan(loan);
    }

    @GetMapping
    public List<Loan> getUserLoans(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return loanService.getUserLoans(user);
    }

    @PutMapping("/{id}/status")
    public Loan updateLoanStatus(@PathVariable Long id, @RequestBody LoanStatusUpdateRequest request) {
        return loanService.updateLoanStatus(id, request.getStatus());
    }
}
