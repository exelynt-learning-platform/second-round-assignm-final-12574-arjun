package com.ecommerce.service;


import com.ecommerce.dto.request.OrderRequest;
import com.ecommerce.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(String userEmail, OrderRequest request);

    List<OrderResponse> getUserOrders(String userEmail);
}
