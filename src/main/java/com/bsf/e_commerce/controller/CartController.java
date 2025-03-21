package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.repository.CartRepository;
import com.bsf.e_commerce.response.CartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> getAll(){
        List<CartResponseDTO> cart = cartRepository.findAll().stream().map(CartResponseDTO::new).collect(Collectors.toList());;
        return ResponseEntity.ok(cart);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{fk_client}")
    public ResponseEntity<List<CartResponseDTO>> getByIdClient(@PathVariable String fk_client){
        List<CartResponseDTO> cart = cartRepository.findByClientId(fk_client);
        return ResponseEntity.ok(cart);
    }
}
