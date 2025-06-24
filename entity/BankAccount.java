package com.ExpenseTracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;
    private String branchName;
    private String accountNumber;
    private String ifscCode;
    private String accountType; // Savings, Current, etc.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
