package com.bsf.e_commerce.config;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoInitializer {
        @Value("${mp.token}")
        private static String accessToken;
        @PostConstruct
        public static void initialize() {
            MercadoPagoConfig.setAccessToken(accessToken);
        }
}
