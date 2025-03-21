package com.bsf.e_commerce.response;

import com.bsf.e_commerce.entity.Cart;
import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Product;

import java.time.LocalDateTime;

public record CartResponseDTO (String id_cart, Client client, Product product, LocalDateTime added_at, int quantity) {
    public CartResponseDTO(Cart cart){
        this(cart.getId_cart(), cart.getClient(), cart.getProduct(), cart.getAdded_at(), cart.getQuantity());
    }
}

