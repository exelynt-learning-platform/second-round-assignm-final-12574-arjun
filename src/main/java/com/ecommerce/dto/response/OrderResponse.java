package com.ecommerce.dto.response;


import com.ecommerce.enums.OrderStatus;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;
    private double totalPrice;
    private OrderStatus status;
    private String shippingAddress;
    private List<OrderItemResponse> items;
}