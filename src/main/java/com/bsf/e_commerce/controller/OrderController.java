package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

}
