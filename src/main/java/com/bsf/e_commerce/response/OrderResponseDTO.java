package com.bsf.e_commerce.response;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Order;
import com.bsf.e_commerce.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(String id_order, Client client, List<Cart> carts, BigDecimal total_price, String paymentMethod, OrderStatus order_status, LocalDateTime order_date) {
    public OrderResponseDTO(Order order){
        this(order.getId_order(), order.getClient(), order.getCartItems(), order.getTotal_price(), order.getPayment_method(), order.getOrder_status(), order.getOrder_date());
    }
}
