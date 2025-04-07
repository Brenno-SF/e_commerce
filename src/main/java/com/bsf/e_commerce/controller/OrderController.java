package com.bsf.e_commerce.controller;


import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Order;
import com.bsf.e_commerce.enums.OrderStatus;
import com.bsf.e_commerce.repository.CartRepository;
import com.bsf.e_commerce.repository.ClientRepository;
import com.bsf.e_commerce.repository.OrderRepository;
import com.bsf.e_commerce.request.OrderRequestDTO;
import com.bsf.e_commerce.response.CartResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientRepository clientRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequest){
        Client client = clientRepository.findById(orderRequest.client().getIdClient()).orElseThrow(() -> new RuntimeException("Client not found"));
        List<Cart> cartItems = cartRepository.findByClientId(orderRequest.client().getIdClient());
        if (cartItems.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        BigDecimal totalPrice = cartItems.stream()
                .map(cart -> cart.getProduct().getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        Order order = new Order();
        order.setClient(client);
        order.setTotal_price(totalPrice);
        order.setPayment_method(orderRequest.payment_method());
        order.setOrder_status(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);

//        for (Cart cart : cartItems) {
//            cart.setOrder(order);
//        }
//        order.setCartItems(cartItems);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

}
