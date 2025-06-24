package com.ExpenseTracker.controller;

import com.ExpenseTracker.Enum.PaymentMode;
import com.ExpenseTracker.dto.BillDto;
import com.ExpenseTracker.entity.Bill;
import com.ExpenseTracker.entity.Category;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.UserRepository;
import com.ExpenseTracker.service.BillService;
import com.ExpenseTracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;
    private final UserRepository userRepository;
    private final CategoryService categoryService;

    @PostMapping
    public Bill addBill(@RequestBody BillDto billDto, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Category category = categoryService.findById(billDto.getCategoryId());

        Bill bill = new Bill();
        bill.setTitle(billDto.getTitle());
        bill.setAmount(billDto.getAmount());
        bill.setDueDate(billDto.getDueDate());
        bill.setPaid(billDto.isPaid());
        try {
            bill.setPaymentMode(PaymentMode.valueOf(billDto.getPaymentMode()));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid payment mode: " + billDto.getPaymentMode());
        }

        bill.setCategory(category);
        bill.setUser(user);


        return billService.addBill(bill);
    }

    @GetMapping
    public List<Bill> getUserBills(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return billService.getUserBills(user);
    }

    @PutMapping("/{id}/pay")
    public Bill markAsPaid(@PathVariable Long id) {
        return billService.markAsPaid(id);
    }
}
