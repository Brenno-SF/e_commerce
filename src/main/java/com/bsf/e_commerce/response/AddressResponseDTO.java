package com.bsf.e_commerce.response;

public record AddressResponseDTO(String street,
                                 String neighborhood,
                                 String zip_code,
                                 String num,
                                 String city,
                                 String fu) {
}
