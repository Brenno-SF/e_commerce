package com.bsf.e_commerce.entity;

import com.bsf.e_commerce.enums.OrderStatus;
import com.bsf.e_commerce.response.CartResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "id_order")
    private String id_order;

    @ManyToOne
    @JoinColumn(name = "fk_client", nullable = false)
    private Client client;


//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @OneToMany
//    @JoinColumn(name = "fk_cart", nullable = false)
//    @JsonIgnore
//    private List<Cart> cartItems;

    @Column(nullable = false)
    private BigDecimal total_price;


    private String payment_method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus order_status;

    @CreationTimestamp
    private LocalDateTime order_date;
}
