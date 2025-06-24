package com.ExpenseTracker.dto;

import com.ExpenseTracker.Enum.LoanStatus;
import lombok.Data;

@Data
public class LoanStatusUpdateRequest {
    private LoanStatus status;
}
