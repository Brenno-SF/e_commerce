package com.bsf.e_commerce.request;

import com.bsf.e_commerce.entity.Client;
import com.bsf.e_commerce.entity.Product;

import java.time.LocalDateTime;

public record CartRequestDTO(String id, Client client, Product product, LocalDateTime added_at, int quantity) {
}
