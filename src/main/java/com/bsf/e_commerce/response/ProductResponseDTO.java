package com.bsf.e_commerce.response;

import com.bsf.e_commerce.entity.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(String id_product, String name_product, BigDecimal price, int stack_quant) {
    public ProductResponseDTO(Product product) {
        this(product.getId_product(), product.getName_product(), product.getPrice(), product.getStack_quant());
    }

}
