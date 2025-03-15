package com.bsf.e_commerce.Address;

public record AddressRequestDTO(String street,
                                String neighborhood,
                                String zip_code,
                                String num,
                                String city,
                                String fu) {
}
