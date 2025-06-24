package com.ExpenseTracker.repository;

import com.ExpenseTracker.entity.Bank;
import com.ExpenseTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    List<Bank> findByUser(User user);
}
