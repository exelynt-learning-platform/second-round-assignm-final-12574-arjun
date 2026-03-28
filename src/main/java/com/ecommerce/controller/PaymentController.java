package com.ecommerce.controller;


import com.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/{orderId}")
    public String createPayment(@PathVariable Long orderId) {
        return service.processPayment(orderId);
    }
}