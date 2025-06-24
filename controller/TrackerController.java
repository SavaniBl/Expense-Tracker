package com.ExpenseTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrackerController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
