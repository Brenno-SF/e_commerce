package com.bsf.e_commerce.request;

import java.math.BigDecimal;

public record ProductRequestDTO(String name_product, BigDecimal price, int stack_quant) {
}
