package com.ExpenseTracker.dto;

import lombok.Data;

@Data
public class BankAccountDto {
    private String bankName;
    private String branchName;
    private String accountNumber;
    private String ifscCode;
    private String accountType;
}
