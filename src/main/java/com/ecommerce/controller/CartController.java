package com.ecommerce.controller;

import com.ecommerce.dto.request.CartRequest;
import com.ecommerce.dto.response.CartResponse;
import com.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping("/add")
    public CartResponse addToCart(@Valid @RequestBody CartRequest request,
                                  Authentication auth) {
        return service.addToCart(auth.getName(), request);
    }

    @GetMapping
    public CartResponse getCart(Authentication auth) {
        return service.getCart(auth.getName());
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponse remove(@PathVariable Long productId,
                               Authentication auth) {
        return service.removeFromCart(auth.getName(), productId);
    }
}
