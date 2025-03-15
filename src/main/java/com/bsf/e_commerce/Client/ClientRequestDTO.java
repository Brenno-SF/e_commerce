package com.bsf.e_commerce.Client;

import com.bsf.e_commerce.Address.AddressRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientRequestDTO(String name_client, String cpf,  String username, String password, String email, LocalDate birth_date, BigDecimal balance, AddressRequestDTO address) {
}
