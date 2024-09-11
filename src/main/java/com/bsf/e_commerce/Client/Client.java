package com.bsf.e_commerce.Client;

import com.bsf.e_commerce.product.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "client")
@Entity(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_client")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id_client;
    private String name_client;
    private String cpf;
    private BigDecimal balance;
    private String username;
    private String password;
    private String email;
    private LocalDate birth_date;

    public Client(ClientRequestDTO data) {
       this.name_client = data.name_client();
       this.cpf = data.cpf();
       this.username = data.username();
       this.password = data.password();
       this.email = data.email();
       this.birth_date = data.birth_date();
       this.balance = data.balance();
    }
}


