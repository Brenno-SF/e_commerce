package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Order;
import com.bsf.e_commerce.enums.OrderStatus;
import com.bsf.e_commerce.repository.CartRepository;
import com.bsf.e_commerce.repository.ClientRepository;
import com.bsf.e_commerce.repository.OrderRepository;
import com.bsf.e_commerce.request.OrderRequestDTO;
import com.bsf.e_commerce.response.OrderResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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



        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        List<OrderResponseDTO> orders = orderRepository.findAll().stream().map(OrderResponseDTO::new).collect(Collectors.toList());;
        return ResponseEntity.ok(orders);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{fk_client}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByIdClient(@PathVariable String fk_client){

        List<OrderResponseDTO> orders = orderRepository.findByClientId(fk_client);

        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id_order}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id_order, @RequestParam OrderStatus status) {
        return orderRepository.findById(id_order).map(order -> {
            order.setOrder_status(status);
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_order}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id_order) {

        Order order = orderRepository.findById(id_order).orElseThrow(() -> new RuntimeException("Order not found with ID: " + id_order));

        orderRepository.delete(order);
        return ResponseEntity.status(HttpStatus.OK).body("The "+ order.getClient().getUsername() + " order has been successfully deleted");
    }


}
