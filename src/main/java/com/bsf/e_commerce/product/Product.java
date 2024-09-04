package com.bsf.e_commerce.product;

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

    public Product(ProductRequestDTO data) {
        this.name_product = data.name_product();
        this.price = data.price();
    }
}


