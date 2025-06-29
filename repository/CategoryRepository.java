package com.ExpenseTracker.repository;

import com.ExpenseTracker.entity.Category;
import com.ExpenseTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
}
