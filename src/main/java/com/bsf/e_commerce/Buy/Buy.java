package com.bsf.e_commerce.Buy;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "buy")
@Entity(name = "buy")
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode(of = "id_buy")
public class Buy {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id_buy;

}
