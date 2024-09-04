package com.bsf.e_commerce.product;

import java.math.BigDecimal;

public record ProductResponseDTO(String id_product, String name_product, BigDecimal price) {
    public ProductResponseDTO(Product product) {
        this(product.getId_product(), product.getName_product(), product.getPrice());
    }

}
