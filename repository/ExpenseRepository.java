package com.ExpenseTracker.repository;

import com.ExpenseTracker.entity.Expense;
import com.ExpenseTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    List<Expense> findByUserAndDateBetween(User user, LocalDateTime start, LocalDateTime end);
}
