package com.ecommerce.service.impl;

import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.response.*;
import com.ecommerce.entity.*;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.repository.*;
import com.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepo;
    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;

    @Override
    public OrderResponse createOrder(String email, OrderRequest req) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Cart cart = cartRepo.findByUser(user).orElseThrow();

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem ci : cart.getItems()) {

            OrderItem oi = OrderItem.builder()
                    .product(ci.getProduct())
                    .quantity(ci.getQuantity())
                    .price(ci.getProduct().getPrice())
                    .build();

            total += oi.getPrice() * oi.getQuantity();
            orderItems.add(oi);
        }

        Order order = Order.builder()
                .user(user)
                .totalPrice(total)
                .status(OrderStatus.CREATED)
                .shippingAddress(req.getShippingAddress())
                .items(orderItems)
                .build();

        orderItems.forEach(i -> i.setOrder(order));

        orderRepo.save(order);

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getUserOrders(String email) {

        User user = userRepo.findByEmail(email).orElseThrow();

        return orderRepo.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OrderResponse mapToResponse(Order o) {

        List<OrderItemResponse> items = o.getItems().stream()
                .map(i -> new OrderItemResponse(
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getPrice()
                ))
                .toList();

        return OrderResponse.builder()
                .orderId(o.getId())
                .totalPrice(o.getTotalPrice())
                .status(o.getStatus())
                .shippingAddress(o.getShippingAddress())
                .items(items)
                .build();
    }
}
