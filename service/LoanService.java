package com.ExpenseTracker.service;

import com.ExpenseTracker.Enum.LoanStatus;
import com.ExpenseTracker.entity.Loan;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getUserLoans(User user) {
        return loanRepository.findByUser(user);
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan updateLoanStatus(Long id, LoanStatus status) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(status);
        return loanRepository.save(loan);
    }
}
