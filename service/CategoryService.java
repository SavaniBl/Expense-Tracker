package com.ExpenseTracker.service;

import com.ExpenseTracker.entity.Category;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getUserCategories(User user) {
        return categoryRepository.findByUser(user);
    }
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

}
