package com.ExpenseTracker.service;

import com.ExpenseTracker.dto.ExpenseDto;
import com.ExpenseTracker.entity.Category;
import com.ExpenseTracker.entity.Expense;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    public List<Expense> getExpensesByUser(User user) {
        return expenseRepo.findByUser(user);
    }

    @PostMapping
    public Expense addExpense(@RequestBody ExpenseDto dto,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Category category = categoryService.findById(dto.getCategory_id());
        Expense expense = Expense.builder()
                .title(dto.getTitle())
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .category(category)
                .recurrence(dto.getRecurrence())
                .date(dto.getDate())
                .user(user)
                .build();
        return expenseRepo.save(expense);
    }
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
    public Expense saveExpense(Expense expense) {
        return expenseRepo.save(expense);
    }
    public List<Expense> getExpensesByDate(User user, LocalDate date) {
        return expenseRepo.findByUserAndDateBetween(
                user,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );
    }

    public List<Expense> getExpensesByMonth(User user, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return expenseRepo.findByUserAndDateBetween(
                user,
                start.atStartOfDay(),
                end.plusDays(1).atStartOfDay()
        );
    }

}
