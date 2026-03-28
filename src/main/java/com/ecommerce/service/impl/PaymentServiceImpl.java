package com.ecommerce.service.impl;

import com.ecommerce.entity.Order;

import com.ecommerce.enums.PaymentStatus;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.PaymentService;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;

    @Override
    public String processPayment(Long orderId) {

        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

            // 💰 amount in paise (Stripe uses smallest currency unit)
            long amount = (long) (order.getTotalPrice() * 100);

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(amount)
                            .setCurrency("inr")
                            .build();

            PaymentIntent intent = PaymentIntent.create(params);

            // 🔄 Update order
            order.setPaymentStatus(PaymentStatus.SUCCESS);
            orderRepository.save(order);

            return intent.getClientSecret();

        } catch (Exception e) {
            throw new RuntimeException("Payment failed: " + e.getMessage());
        }
    }


}