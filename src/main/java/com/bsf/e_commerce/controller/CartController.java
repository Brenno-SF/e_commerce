package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Product;
import com.bsf.e_commerce.repository.CartRepository;
import com.bsf.e_commerce.repository.ClientRepository;
import com.bsf.e_commerce.repository.ProductRepository;
import com.bsf.e_commerce.request.CartRequestDTO;
import com.bsf.e_commerce.response.CartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> saveCart(@RequestBody CartRequestDTO data){
        Client client = clientRepository.findById(data.client().getIdClient()).orElseThrow(() -> new RuntimeException("Client not found"));
        Product product = productRepository.findById(data.product().getId_product()).orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = new Cart();
        cart.setClient(client);
        cart.setProduct(product);
        cart.setQuantity(data.quantity());
        cart.setAdded_at(LocalDateTime.now());
        cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cart + " saved successfully");
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id_cart}")
    public ResponseEntity<String> deleteClient(@PathVariable("id_cart") String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found with ID: " + id));

        cartRepository.delete(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cart.getProduct().getName_product() + " deleted successfully");
    }
}
