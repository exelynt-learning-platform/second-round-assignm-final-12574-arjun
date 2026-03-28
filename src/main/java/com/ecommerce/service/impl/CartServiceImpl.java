package com.ecommerce.service.impl;

import com.ecommerce.dto.request.CartRequest;
import com.ecommerce.dto.response.*;
import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import com.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final CartItemRepository itemRepo;

    @Override
    public CartResponse addToCart(String email, CartRequest req) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Cart cart = cartRepo.findByUser(user).orElseGet(() ->
                cartRepo.save(Cart.builder().user(user).build()));

        Product product = productRepo.findById(req.getProductId()).orElseThrow();

        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(req.getQuantity())
                .build();

        itemRepo.save(item);

        return buildCartResponse(cart);
    }

    @Override
    public CartResponse getCart(String email) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Cart cart = cartRepo.findByUser(user).orElseThrow();

        return buildCartResponse(cart);
    }

    @Override
    public CartResponse removeFromCart(String email, Long productId) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Cart cart = cartRepo.findByUser(user).orElseThrow();

        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));

        cartRepo.save(cart);

        return buildCartResponse(cart);
    }

    private CartResponse buildCartResponse(Cart cart) {

        List<CartItemResponse> items = new ArrayList<>();
        double total = 0;

        for (CartItem i : cart.getItems()) {
            double itemTotal = i.getProduct().getPrice() * i.getQuantity();
            total += itemTotal;

            items.add(CartItemResponse.builder()
                    .productId(i.getProduct().getId())
                    .productName(i.getProduct().getName())
                    .price(i.getProduct().getPrice())
                    .quantity(i.getQuantity())
                    .total(itemTotal)
                    .build());
        }

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .totalPrice(total)
                .build();
    }
}