package com.ExpenseTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillDto {
    private String title;
    private BigDecimal amount;
    private LocalDate dueDate;
    private boolean isPaid;
    private String paymentMode;
    private Long categoryId;  //
}
