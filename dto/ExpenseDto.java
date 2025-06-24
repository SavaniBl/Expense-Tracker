package com.ExpenseTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private String title;
    private BigDecimal amount;
    private String description;
    private Long category_id;
    private String recurrence;
    private LocalDateTime date;

}
