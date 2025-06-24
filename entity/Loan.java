package com.ExpenseTracker.entity;

import com.ExpenseTracker.Enum.LoanStatus;
import com.ExpenseTracker.Enum.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal amount;

    private double interestRate;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;  // e.g., PENDING, PAID, OVERDUE

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
