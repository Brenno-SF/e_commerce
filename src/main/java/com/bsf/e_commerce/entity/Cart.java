package com.bsf.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name="fk_client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="fk_product",nullable = false)
    private Product product;

    @CreationTimestamp
    private LocalDateTime added_at;
    private int quantity;
    }
