package com.ExpenseTracker.repository;

import com.ExpenseTracker.entity.Bill;
import com.ExpenseTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUser(User user);
}
