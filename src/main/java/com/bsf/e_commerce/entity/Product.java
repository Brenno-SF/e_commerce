package com.bsf.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id_product;
    private String name_product;
    private BigDecimal price;
    private int stack_quant;

    public Product(com.bsf.e_commerce.request.product.ProductRequestDTO data) {
        this.name_product = data.name_product();
        this.price = data.price();
        this.stack_quant = data.stack_quant();
    }
}


