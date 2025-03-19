package com.bsf.e_commerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "buy")
@Entity(name = "buy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_buy")
public class Buy {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id_buy;
    private String fk_client;
    private String fk_product;
    private String buy_date;
    private int quantity;    //id_buy	fk_client	fk_product	buy_date	quantity

}
