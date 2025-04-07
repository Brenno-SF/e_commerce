package com.bsf.e_commerce.controller;

import com.bsf.e_commerce.entity.Cart;
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
import java.util.Optional;
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
    public ResponseEntity<List<CartResponseDTO>> getAllCarts(){
        List<CartResponseDTO> cart = cartRepository.findAll().stream().map(CartResponseDTO::new).collect(Collectors.toList());;
        return ResponseEntity.ok(cart);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{fk_client}")
    public ResponseEntity<List<Cart>> getByIdClient(@PathVariable String fk_client){
        List<Cart> cart = cartRepository.findByClientId(fk_client);

        return ResponseEntity.ok(cart);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> saveCart(@RequestBody CartRequestDTO cartRequestDTO){
        Optional<Product> productOptional = productRepository.findById(cartRequestDTO.product().getId_product());
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product product = productOptional.get();

        Optional<Cart> existingCartItem = cartRepository.findByClientAndProduct(cartRequestDTO.client(), cartRequestDTO.product());
        if (existingCartItem.isPresent()) {
            Cart cart = existingCartItem.get();
            cart.setQuantity(cart.getQuantity() + cartRequestDTO.quantity());
            cartRepository.save(cart);
            return ResponseEntity.ok("The quantity of the "+ cart.getProduct().getName_product()+ " has been successfully updated..");
        }
        Cart cart = new Cart();
        cart.setClient(cartRequestDTO.client());
        cart.setProduct(product);
        cart.setQuantity(cartRequestDTO.quantity());
        cart.setAdded_at(LocalDateTime.now());
        cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body("The " + cart.getProduct().getName_product() + " has been successfully saved");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id_cart}")
    public ResponseEntity<String> updateCartItem(@PathVariable String id_cart, @RequestBody CartRequestDTO cartRequestDTO) {
        Cart cart = cartRepository.findById(id_cart).orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cart.setQuantity(cartRequestDTO.quantity());

        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.OK).body("The "+ cart.getProduct().getName_product() + " has been successfully updated");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("item/{id_cart}")
    public ResponseEntity<String> deleteItemCart(@PathVariable("id_cart") String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found with ID: " + id));

        cartRepository.delete(cart);

        return ResponseEntity.status(HttpStatus.OK).body("The "+cart.getProduct().getName_product() + " has been successfully deleted");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/clear/{fk_client}")
    public ResponseEntity<String> clearCart(@PathVariable("fk_client") String id) {
        cartRepository.deleteByClientId(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cart has been successfully cleaned");
    }
}
