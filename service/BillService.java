package com.ExpenseTracker.service;

import com.ExpenseTracker.entity.Bill;
import com.ExpenseTracker.entity.User;
import com.ExpenseTracker.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    public List<Bill> getUserBills(User user) {
        return billRepository.findByUser(user);
    }

    public Bill markAsPaid(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow();
        bill.setPaid(true);
        return billRepository.save(bill);
    }
}
