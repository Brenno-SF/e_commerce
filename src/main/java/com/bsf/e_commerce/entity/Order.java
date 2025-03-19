package com.bsf.e_commerce.entity;

import com.bsf.e_commerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "orders")
@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_order;

    @ManyToOne
    @JoinColumn(name = "fk_client", nullable = false)
    private Client client;

    @OneToMany
    @JoinColumn(name = "fk_cart")
    @JsonIgnore
    private List<Cart> cartItems;

    @Column(nullable = false)
    private BigDecimal total_price;


    private String payment_method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus order_status;

    private LocalDateTime order_date;
}
