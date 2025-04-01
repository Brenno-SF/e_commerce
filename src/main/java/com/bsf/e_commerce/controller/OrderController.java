package com.bsf.e_commerce.controller;


import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Order;
import com.bsf.e_commerce.repository.CartRepository;
import com.bsf.e_commerce.repository.ClientRepository;
import com.bsf.e_commerce.repository.OrderRepository;
import com.bsf.e_commerce.response.CartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestParam String idClient){
//        Client client = clientRepository.findById(idClient).orElseThrow(() -> new RuntimeException("Client not found"));
//        List<CartResponseDTO> carts = cartRepository.findByClientId(idClient);
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("The " + carts.getProduct().getName_product() + " has been successfully saved");
//    }

}
