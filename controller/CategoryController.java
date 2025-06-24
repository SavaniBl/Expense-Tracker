package com.ExpenseTracker.controller;

import com.ExpenseTracker.entity.Category;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.service.CategoryService;
import com.ExpenseTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping
    public Category addCategory(@RequestBody Category category, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        category.setUser(user);
        return categoryService.addCategory(category);
    }

    @GetMapping
    public List<Category> getUserCategories(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return categoryService.getUserCategories(user);
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        Category category = categoryService.findById(id);
        if (!category.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to category");
        }
        return category;
    }


}
