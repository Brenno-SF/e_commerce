package com.bsf.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cart")
@Entity(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_cart")

public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id_cart;
    private String fk_client;
    private String fk_product;
    private String buy_date;
    private int quantity;

}
