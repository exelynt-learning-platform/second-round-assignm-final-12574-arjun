package com.ecommerce.service;

import com.ecommerce.dto.request.CartRequest;
import com.ecommerce.dto.response.CartResponse;

public interface CartService {

    CartResponse addToCart(String userEmail, CartRequest request);

    CartResponse getCart(String userEmail);

    CartResponse removeFromCart(String userEmail, Long productId);
}