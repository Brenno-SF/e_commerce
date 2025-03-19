package com.bsf.e_commerce.entity;
import com.bsf.e_commerce.request.AddressResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "address")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_address;
    private String street;
    private String neighborhood;
    private String zip_code;
    private String num;
    private String city;
    private String fu;

    @Setter
    @OneToOne
    @JoinColumn(name = "fk_clientA", referencedColumnName = "id_client")
    @JsonIgnore
    private Client client;

    public Address(AddressResponseDTO data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zip_code = data.zip_code();
        this.num = data.num();
        this.city = data.city();
        this.fu = data.fu();
    }

}
