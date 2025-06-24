package com.ExpenseTracker.controller;


import com.ExpenseTracker.dto.ExpenseDto;
import com.ExpenseTracker.entity.Category;
import com.ExpenseTracker.entity.Expense;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.service.CategoryService;
import com.ExpenseTracker.service.ExpenseService;
import com.ExpenseTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Expense> getUserExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return expenseService.getExpensesByUser(user);
    }
    @PostMapping("/add")
    public Expense addExpense(@RequestBody ExpenseDto dto,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Category category = categoryService.findById(dto.getCategory_id());

        Expense expense = Expense.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .user(user)
                .category(category)
                .build();
        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully.";
    }



}
