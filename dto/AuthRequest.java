package com.ExpenseTracker.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
