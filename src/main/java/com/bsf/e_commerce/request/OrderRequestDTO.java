package com.bsf.e_commerce.request;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequestDTO(String id_order, Client client, List<Cart> carts, BigDecimal total_price, String payment_method, OrderStatus order_status, LocalDateTime order_date) {
}
