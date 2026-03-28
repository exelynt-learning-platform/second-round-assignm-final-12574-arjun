package com.ecommerce.controller;

import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request,
                                     Authentication auth) {
        return service.createOrder(auth.getName(), request);
    }

    @GetMapping
    public List<OrderResponse> getOrders(Authentication auth) {
        return service.getUserOrders(auth.getName());
    }
}