package com.bsf.e_commerce.Client;

import com.bsf.e_commerce.Address.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientResponseDTO(String id_client, String name_client, String cpf, String username, String password, String email, LocalDate birth_date, BigDecimal balance, Address address) {
    public ClientResponseDTO(Client client) {
        this(client.getId_client(), client.getName_client(), client.getCpf(), client.getUsername(), client.getPassword(), client.getEmail(), client.getBirth_date(), client.getBalance(), client.getAddress());
    }
}
