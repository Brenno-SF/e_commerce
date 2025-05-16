package com.bsf.e_commerce.config;

import lombok.Builder;

@Builder
public record JWTClientData(String id, String name,String username){
}
