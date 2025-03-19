package com.bsf.e_commerce.request;

import com.bsf.e_commerce.response.AddressResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientRequestDTO(String name_client, String cpf,  String username, String password, String email, LocalDate birth_date, BigDecimal balance, AddressResponseDTO address) {
}
